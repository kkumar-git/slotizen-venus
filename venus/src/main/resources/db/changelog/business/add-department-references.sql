-- liquibase formatted sql

-- changeset slotizen:add-department-references

-- Add department_id column to business_service table
ALTER TABLE business_service ADD COLUMN department_id BIGINT;

-- Add department_id column to business_staff_members table  
ALTER TABLE business_staff_members ADD COLUMN department_id BIGINT;

-- Add foreign key constraints
ALTER TABLE business_service 
ADD CONSTRAINT fk_service_department 
FOREIGN KEY (department_id) REFERENCES departments(id) ON DELETE SET NULL;

ALTER TABLE business_staff_members 
ADD CONSTRAINT fk_staff_department 
FOREIGN KEY (department_id) REFERENCES departments(id) ON DELETE SET NULL;

-- Add indexes for performance
CREATE INDEX idx_service_department_id ON business_service(department_id);
CREATE INDEX idx_staff_department_id ON business_staff_members(department_id);

-- Add comments
COMMENT ON COLUMN business_service.department_id IS 'Reference to department this service belongs to';
COMMENT ON COLUMN business_staff_members.department_id IS 'Reference to department this staff member belongs to';