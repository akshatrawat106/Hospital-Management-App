import { useState, useEffect } from "react";
import { appointmentApi, doctorApi, patientApi } from "@/lib/api";
import { DoctorDTO, PatientDTO } from "@/types";
import { Button } from "@/components/ui/button";
import { Input } from "@/components/ui/input";
import { Label } from "@/components/ui/label";
import { Select, SelectContent, SelectItem, SelectTrigger, SelectValue } from "@/components/ui/select";
import { CalendarCheck } from "lucide-react";
import { toast } from "sonner";

const AppointmentsPage = () => {
  const [doctors, setDoctors] = useState<DoctorDTO[]>([]);
  const [patients, setPatients] = useState<PatientDTO[]>([]);
  const [loading, setLoading] = useState(false);
  const [doctorId, setDoctorId] = useState("");
  const [patientId, setPatientId] = useState("");
  const [date, setDate] = useState("");
  const [time, setTime] = useState("");
  const [result, setResult] = useState<string | null>(null);

  useEffect(() => {
    const load = async () => {
      try {
        const [docs, pats] = await Promise.all([doctorApi.getAll(), patientApi.getAll()]);
        setDoctors(docs);
        setPatients(pats);
      } catch (err: any) {
        toast.error(err?.message || "Failed to load data");
      }
    };
    load();
  }, []);

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    if (!doctorId || !patientId || !date || !time) {
      toast.error("All fields are required");
      return;
    }
    setLoading(true);
    try {
      const appt = await appointmentApi.create(Number(doctorId), Number(patientId), date, time);
      setResult(`Appointment #${appt.appoint_id} booked — ${appt.patientname} with Dr. ${appt.doctorname} on ${appt.appointment_date} at ${appt.appointment_time}`);
      toast.success("Appointment booked!");
    } catch (err: any) {
      toast.error(err?.message || "Failed to book appointment");
    } finally {
      setLoading(false);
    }
  };

  return (
    <div>
      <div className="page-header">
        <h1 className="page-title">Appointments</h1>
        <p className="page-subtitle">Book appointments for patients with doctors</p>
      </div>

      <div className="max-w-xl">
        <div className="bg-card rounded-xl border border-border p-6">
          <div className="flex items-center gap-3 mb-6">
            <div className="w-10 h-10 rounded-lg bg-primary/10 flex items-center justify-center">
              <CalendarCheck className="w-5 h-5 text-primary" />
            </div>
            <h2 className="text-lg font-display font-semibold">Book Appointment</h2>
          </div>

          <form onSubmit={handleSubmit} className="space-y-4">
            <div className="space-y-2">
              <Label>Doctor</Label>
              <Select value={doctorId} onValueChange={setDoctorId}>
                <SelectTrigger><SelectValue placeholder="Select a doctor" /></SelectTrigger>
                <SelectContent>
                  {doctors.filter((d) => d.availability).map((d) => (
                    <SelectItem key={d.doctorId} value={String(d.doctorId)}>Dr. {d.name} — {d.specialization}</SelectItem>
                  ))}
                </SelectContent>
              </Select>
            </div>

            <div className="space-y-2">
              <Label>Patient</Label>
              <Select value={patientId} onValueChange={setPatientId}>
                <SelectTrigger><SelectValue placeholder="Select a patient" /></SelectTrigger>
                <SelectContent>
                  {patients.map((p) => (
                    <SelectItem key={p.patientId} value={String(p.patientId)}>{p.name}</SelectItem>
                  ))}
                </SelectContent>
              </Select>
            </div>

            <div className="grid grid-cols-2 gap-4">
              <div className="space-y-2">
                <Label>Date</Label>
                <Input type="date" value={date} onChange={(e) => setDate(e.target.value)} required />
              </div>
              <div className="space-y-2">
                <Label>Time</Label>
                <Input type="time" value={time} onChange={(e) => setTime(e.target.value)} required />
              </div>
            </div>

            <Button type="submit" className="w-full" disabled={loading}>
              {loading ? "Booking..." : "Book Appointment"}
            </Button>
          </form>

          {result && (
            <div className="mt-4 p-4 rounded-lg bg-accent text-accent-foreground text-sm">
              ✅ {result}
            </div>
          )}
        </div>
      </div>
    </div>
  );
};

export default AppointmentsPage;
