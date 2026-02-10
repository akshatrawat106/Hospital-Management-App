import { LoginRequest, LoginResponse, DepartmentDTO, DoctorDTO, PatientDTO, AppointmentDTO, BillingDTO, BillingReqDTO, UserRequest } from "@/types";

const API_BASE = "http://localhost:8080";

function getToken(): string | null {
  const stored = localStorage.getItem("hospital_auth");
  if (!stored) return null;
  try {
    return JSON.parse(stored).token;
  } catch {
    return null;
  }
}

async function request<T>(path: string, options: RequestInit = {}): Promise<T> {
  const token = getToken();
  const headers: Record<string, string> = {
    "Content-Type": "application/json",
    ...((options.headers as Record<string, string>) || {}),
  };
  if (token) {
    headers["Authorization"] = `Bearer ${token}`;
  }

  const res = await fetch(`${API_BASE}${path}`, {
    ...options,
    headers,
  });

  if (!res.ok) {
    const text = await res.text().catch(() => "Request failed");
    throw new Error(text || `HTTP ${res.status}`);
  }

  const contentType = res.headers.get("content-type");
  if (contentType?.includes("application/json")) {
    return res.json();
  }
  return res.text() as unknown as T;
}

// Auth
export const authApi = {
  login: (data: LoginRequest) =>
    request<LoginResponse>("/auth/login", { method: "POST", body: JSON.stringify(data) }),
};

// Departments
export const departmentApi = {
  getAll: () => request<DepartmentDTO[]>("/department/all"),
  register: (data: DepartmentDTO) =>
    request<DepartmentDTO>("/department/register", { method: "POST", body: JSON.stringify(data) }),
  update: (deptId: number, data: DepartmentDTO) =>
    request<DepartmentDTO>(`/department/update/${deptId}`, { method: "PUT", body: JSON.stringify(data) }),
  delete: (deptId: number) =>
    request<string>(`/department/delete?deptid=${deptId}`, { method: "DELETE" }),
};

// Doctors
export const doctorApi = {
  getAll: () => request<DoctorDTO[]>("/doctor"),
  getById: (id: number) => request<DoctorDTO>(`/doctor/${id}`),
  getByDepartment: (deptId: number) => request<DoctorDTO[]>(`/doctor/department/${deptId}`),
  getAvailable: () => request<DoctorDTO[]>("/doctor/available"),
  searchBySpecialization: (spec: string) => request<DoctorDTO[]>(`/doctor/search?specialization=${encodeURIComponent(spec)}`),
  register: (data: DoctorDTO) =>
    request<DoctorDTO>("/doctor/register", { method: "POST", body: JSON.stringify(data) }),
  update: (id: number, data: DoctorDTO) =>
    request<DoctorDTO>(`/doctor/${id}`, { method: "PUT", body: JSON.stringify(data) }),
  setAvailability: (id: number, available: boolean) =>
    request<DoctorDTO>(`/doctor/${id}/availability?available=${available}`, { method: "PUT" }),
  delete: (id: number) => request<boolean>(`/doctor/${id}`, { method: "DELETE" }),
};

// Patients
export const patientApi = {
  getAll: () => request<PatientDTO[]>("/patient"),
  getById: (id: number) => request<PatientDTO>(`/patient/${id}`),
  getByDoctor: (doctorId: number) => request<PatientDTO[]>(`/patient/doctor/${doctorId}?doctorId=${doctorId}`),
  register: (data: PatientDTO) =>
    request<PatientDTO>("/patient", { method: "POST", body: JSON.stringify(data) }),
  update: (id: number, data: PatientDTO) =>
    request<PatientDTO>(`/patient/${id}`, { method: "PUT", body: JSON.stringify(data) }),
  discharge: (id: number) =>
    request<PatientDTO>(`/patient/${id}/discharge`, { method: "PUT" }),
  delete: (id: number) => request<boolean>(`/patient/${id}`, { method: "DELETE" }),
};

// Appointments
export const appointmentApi = {
  create: (doctorId: number, patientId: number, date: string, time: string) =>
    request<AppointmentDTO>(
      `/appointment?doctorId=${doctorId}&patientId=${patientId}&appointmentDate=${date}&appointmentTime=${time}`,
      { method: "POST" }
    ),
};

// Billing
export const billingApi = {
  create: (data: BillingReqDTO) =>
    request<BillingDTO>("/billing/create", { method: "POST", body: JSON.stringify(data) }),
  pay: (billId: number) =>
    request<string>(`/billing/pay/${billId}`, { method: "PUT" }),
  getById: (billId: number) => request<BillingDTO>(`/billing/${billId}`),
  getByPatientName: (name: string) => request<BillingDTO[]>(`/billing/patient/name/${encodeURIComponent(name)}`),
  getByPatientId: (id: number) => request<BillingDTO[]>(`/billing/patient/id/${id}`),
};

// Users
export const userApi = {
  add: (data: UserRequest) =>
    request<string>("/user/add", { method: "POST", body: JSON.stringify(data) }),
};
