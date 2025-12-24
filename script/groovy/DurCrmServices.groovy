// Groovy service implementations for DurCrmServices

def updateVehicle() {
    def vehicleId = context.vehicleId
    def mileage = context.mileage
    def nextServiceDate = context.nextServiceDate
    def notes = context.notes
    
    def vehicle = ec.entity.find("durion.crm.DurVehicle")
            .condition("vehicleId", vehicleId).one()
    
    if (!vehicle) {
        ec.message.addError("Vehicle not found: ${vehicleId}")
        return [success: false]
    }
    
    if (mileage != null) {
        if (mileage < 0) {
            ec.message.addError("Mileage cannot be negative")
            return [success: false]
        }
        vehicle.mileage = mileage
    }
    
    if (nextServiceDate != null) {
        vehicle.nextServiceDate = nextServiceDate
    }
    
    if (notes != null) {
        vehicle.notes = notes
    }
    
    vehicle.updatedDate = ec.user.nowTimestamp
    vehicle.update()
    
    ec.logger.info("Vehicle ${vehicleId} updated successfully")
    
    return [success: true]
}

def getCustomerSummary() {
    def customerId = context.customerId
    
    def customer = ec.entity.find("durion.crm.DurCustomer")
            .condition("customerId", customerId).one()
    
    if (!customer) {
        ec.message.addError("Customer not found: ${customerId}")
        return [:]
    }
    
    // Get vehicles for this customer
    def vehicles = ec.entity.find("durion.crm.DurVehicle")
            .condition("customerId", customerId)
            .list()
    
    // Get fleets for this customer
    def fleets = ec.entity.find("durion.crm.DurFleet")
            .condition("customerId", customerId)
            .list()
    
    // Get contact mechanisms
    def contacts = ec.entity.find("durion.crm.DurContactMech")
            .condition("customerId", customerId)
            .list()
    
    // Count fleet vehicles
    def totalFleetVehicles = 0
    fleets.each { fleet ->
        def fleetVehicleCount = ec.entity.find("durion.crm.DurVehicle")
                .condition("fleetId", fleet.fleetId)
                .count()
        totalFleetVehicles += fleetVehicleCount
    }
    
    def totalVehicles = vehicles.size() + totalFleetVehicles
    
    return [
        customer: customer,
        vehicles: vehicles,
        fleets: fleets,
        contacts: contacts,
        totalVehicles: totalVehicles,
        totalFleets: fleets.size()
    ]
}
