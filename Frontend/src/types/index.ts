// Auth
export interface LoginRequest {
  username: string;
  password: string;
}

export interface LoginResponse {
  token: string;
}

export interface AuthUser {
  username: string;
  role: string;
  token: string;
}

// Department
export interface DepartmentDTO {
  dept_id: number | null;
  dept_name: string;
  location: string;
  dept_head: string;
}

// Doctor
export interface DoctorDTO {
  doctorId: number | null;
  name: string;
  specialization: string;
  contact_no: string;
  email_id: string;
  departmentId: number;
  availability: boolean;
}

// Patient
export interface PatientDTO {
  patientId: number | null;
  name: string;
  age: number;
  gender: string;
  contact_no: string;
  address: string;
  email_id: string;
  release_date: string | null;
  admission_date: string | null;
  blood_group: string;
  doctorname: string;
  doctorID: number;
}

// Appointment
export interface AppointmentDTO {
  appoint_id: number | null;
  patientId: number;
  patientname: string;
  doctorId: number;
  doctorname: string;
  appointment_date: string;
  appointment_time: string;
  status: string;
}

// Billing
export interface BillingDTO {
  billId: number | null;
  patientId: number;
  patientname: string;
  billDate: string;
  roomCharges: number;
  medicineCharges: number;
  doctorCharges: number;
  totalCharges: number;
  status: boolean;
}

export interface BillingReqDTO {
  patientId: number;
  billDate: string;
  roomCharges: number;
  medicineCharges: number;
  doctorCharges: number;
}

// Record
export interface RecordDTO {
  recordId: number | null;
  patientname: string;
  patientId: number;
  doctorname: string;
  doctorId: number;
  diagnosis: string;
  prescription: string;
  treatment: string;
  record_date: string;
}

// User
export interface UserRequest {
  username: string;
  password: string;
  role: string;
}
