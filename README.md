# Durion Crm

Customer Relationship Management

## Overview

This component is part of the Durion ERP system built on the Moqui Framework.

## Purpose

Provide the Moqui-side entities, services, and screens for customer relationship management, reflecting CRM domain business rules around party master data, contactability, vehicle/customer asset links, and auditable relationship management.

## Scope

In scope:
- Party/person and organization customer master records
- Contact points (email/phone) with primary-per-kind constraints and validation
- Vehicle records and customer/vehicle associations with effective dating
- Party relationships (commercial accounts, billing contacts) with non-overlap invariants
- Communication preferences/consent surfaces and audit-friendly updates

Out of scope:
- Work order execution state and workflows (owned by Work Execution)
- Invoice lifecycle, payment capture, and accounting posting (owned by Billing/Accounting)
- Authentication and authorization enforcement (owned by Security; surfaced/consumed here)

## Structure

- `data/` - Seed and demo data
- `entity/` - Entity definitions
- `screen/` - UI screens and forms
- `service/` - Service definitions
- `src/` - Groovy/Java source code
- `template/` - Email and document templates
- `test/` - Test specifications

## Dependencies

See `component.xml` for component dependencies.

## Installation

This component is managed via `myaddons.xml` in the Moqui project root.

## License

Same as Durion project.

---

**Last Updated:** December 09, 2025
