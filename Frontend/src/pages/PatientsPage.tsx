import { useState, useEffect } from "react";
import { patientApi, doctorApi } from "@/lib/api";
import { PatientDTO, DoctorDTO } from "@/types";
import { Button } from "@/components/ui/button";
import { Input } from "@/components/ui/input";
import { Label } from "@/components/ui/label";
import { Dialog, DialogContent, DialogHeader, DialogTitle, DialogTrigger } from "@/components/ui/dialog";
import { Select, SelectContent, SelectItem, SelectTrigger, SelectValue } from "@/components/ui/select";
import { Plus, Search, Trash2, Edit2, UserCheck } from "lucide-react";
import { toast } from "sonner";

const PatientsPage = () => {
  const [patients, setPatients] = useState<PatientDTO[]>([]);
  const [doctors, setDoctors] = useState<DoctorDTO[]>([]);
  const [loading, setLoading] = useState(false);
  const [search, setSearch] = useState("");
  const [dialogOpen, setDialogOpen] = useState(false);
  const [editPatient, setEditPatient] = useState<PatientDTO | null>(null);
  const [form, setForm] = useState<PatientDTO>({
    patientId: null, name: "", age: 0, gender: "Male", contact_no: "", address: "",
    email_id: "", release_date: null, admission_date: null, blood_group: "", doctorname: "", doctorID: 0,
  });

  const fetchData = async () => {
    setLoading(true);
    try {
      const [pats, docs] = await Promise.all([patientApi.getAll(), doctorApi.getAll()]);
      setPatients(pats);
      setDoctors(docs);
    } catch (err: any) {
      toast.error(err?.message || "Failed to load data");
    } finally {
      setLoading(false);
    }
  };

  useEffect(() => { fetchData(); }, []);

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    try {
      if (editPatient) {
        await patientApi.update(editPatient.patientId!, form);
        toast.success("Patient updated");
      } else {
        await patientApi.register(form);
        toast.success("Patient registered");
      }
      setDialogOpen(false);
      setEditPatient(null);
      fetchData();
    } catch (err: any) {
      toast.error(err?.message || "Failed to save");
    }
  };

  const handleDischarge = async (id: number) => {
    if (!confirm("Discharge this patient?")) return;
    try {
      await patientApi.discharge(id);
      toast.success("Patient discharged");
      fetchData();
    } catch (err: any) {
      toast.error(err?.message || "Failed to discharge");
    }
  };

  const handleDelete = async (id: number) => {
    if (!confirm("Delete this patient?")) return;
    try {
      await patientApi.delete(id);
      toast.success("Patient deleted");
      fetchData();
    } catch (err: any) {
      toast.error(err?.message || "Failed to delete");
    }
  };

  const openCreate = () => {
    setEditPatient(null);
    setForm({ patientId: null, name: "", age: 0, gender: "Male", contact_no: "", address: "", email_id: "", release_date: null, admission_date: null, blood_group: "", doctorname: "", doctorID: 0 });
    setDialogOpen(true);
  };

  const openEdit = (p: PatientDTO) => {
    setEditPatient(p);
    setForm({ ...p });
    setDialogOpen(true);
  };

  const filtered = patients.filter((p) =>
    p.name?.toLowerCase().includes(search.toLowerCase()) ||
    p.doctorname?.toLowerCase().includes(search.toLowerCase())
  );

  return (
    <div>
      <div className="page-header flex flex-col sm:flex-row sm:items-center sm:justify-between gap-4">
        <div>
          <h1 className="page-title">Patients</h1>
          <p className="page-subtitle">Register, manage, and discharge patients</p>
        </div>
        <div className="flex items-center gap-3">
          <div className="relative">
            <Search className="absolute left-3 top-1/2 -translate-y-1/2 h-4 w-4 text-muted-foreground" />
            <Input placeholder="Search patients..." value={search} onChange={(e) => setSearch(e.target.value)} className="pl-9 w-56" />
          </div>
          <Dialog open={dialogOpen} onOpenChange={setDialogOpen}>
            <DialogTrigger asChild>
              <Button onClick={openCreate}><Plus className="w-4 h-4 mr-2" /> Add Patient</Button>
            </DialogTrigger>
            <DialogContent className="max-w-lg">
              <DialogHeader>
                <DialogTitle>{editPatient ? "Edit Patient" : "Register Patient"}</DialogTitle>
              </DialogHeader>
              <form onSubmit={handleSubmit} className="space-y-4">
                <div className="grid grid-cols-2 gap-4">
                  <div className="space-y-2">
                    <Label>Name</Label>
                    <Input value={form.name} onChange={(e) => setForm({ ...form, name: e.target.value })} required />
                  </div>
                  <div className="space-y-2">
                    <Label>Age</Label>
                    <Input type="number" value={form.age || ""} onChange={(e) => setForm({ ...form, age: Number(e.target.value) })} required />
                  </div>
                  <div className="space-y-2">
                    <Label>Gender</Label>
                    <Select value={form.gender} onValueChange={(v) => setForm({ ...form, gender: v })}>
                      <SelectTrigger><SelectValue /></SelectTrigger>
                      <SelectContent>
                        <SelectItem value="Male">Male</SelectItem>
                        <SelectItem value="Female">Female</SelectItem>
                        <SelectItem value="Other">Other</SelectItem>
                      </SelectContent>
                    </Select>
                  </div>
                  <div className="space-y-2">
                    <Label>Blood Group</Label>
                    <Select value={form.blood_group} onValueChange={(v) => setForm({ ...form, blood_group: v })}>
                      <SelectTrigger><SelectValue placeholder="Select" /></SelectTrigger>
                      <SelectContent>
                        {["A+", "A-", "B+", "B-", "AB+", "AB-", "O+", "O-"].map((bg) => (
                          <SelectItem key={bg} value={bg}>{bg}</SelectItem>
                        ))}
                      </SelectContent>
                    </Select>
                  </div>
                  <div className="space-y-2">
                    <Label>Contact</Label>
                    <Input value={form.contact_no} onChange={(e) => setForm({ ...form, contact_no: e.target.value })} required />
                  </div>
                  <div className="space-y-2">
                    <Label>Email</Label>
                    <Input type="email" value={form.email_id} onChange={(e) => setForm({ ...form, email_id: e.target.value })} required />
                  </div>
                </div>
                <div className="space-y-2">
                  <Label>Address</Label>
                  <Input value={form.address} onChange={(e) => setForm({ ...form, address: e.target.value })} required />
                </div>
                <div className="space-y-2">
                  <Label>Assigned Doctor</Label>
                  <Select value={String(form.doctorID || "")} onValueChange={(v) => setForm({ ...form, doctorID: Number(v) })}>
                    <SelectTrigger><SelectValue placeholder="Select doctor" /></SelectTrigger>
                    <SelectContent>
                      {doctors.map((d) => (
                        <SelectItem key={d.doctorId} value={String(d.doctorId)}>{d.name} — {d.specialization}</SelectItem>
                      ))}
                    </SelectContent>
                  </Select>
                </div>
                <Button type="submit" className="w-full">{editPatient ? "Update" : "Register"}</Button>
              </form>
            </DialogContent>
          </Dialog>
        </div>
      </div>

      <div className="bg-card rounded-xl border border-border overflow-hidden">
        {loading ? (
          <div className="p-12 text-center text-muted-foreground">Loading...</div>
        ) : filtered.length === 0 ? (
          <div className="p-12 text-center text-muted-foreground">No patients found.</div>
        ) : (
          <div className="overflow-x-auto">
            <table className="data-table">
              <thead>
                <tr><th>ID</th><th>Name</th><th>Age</th><th>Gender</th><th>Doctor</th><th>Actions</th></tr>
              </thead>
              <tbody>
                {filtered.map((p) => (
                  <tr key={p.patientId}>
                    <td className="font-medium">{p.patientId}</td>
                    <td className="font-medium">{p.name}</td>
                    <td>{p.age}</td>
                    <td>{p.gender}</td>
                    <td>{p.doctorname}</td>
                    <td>
                      <div className="flex items-center gap-1">
                        <Button variant="ghost" size="icon" className="h-8 w-8" onClick={() => openEdit(p)}>
                          <Edit2 className="h-3.5 w-3.5" />
                        </Button>
                        <Button variant="ghost" size="icon" className="h-8 w-8 text-primary" onClick={() => handleDischarge(p.patientId!)} title="Discharge">
                          <UserCheck className="h-3.5 w-3.5" />
                        </Button>
                        <Button variant="ghost" size="icon" className="h-8 w-8 text-destructive hover:text-destructive" onClick={() => handleDelete(p.patientId!)}>
                          <Trash2 className="h-3.5 w-3.5" />
                        </Button>
                      </div>
                    </td>
                  </tr>
                ))}
              </tbody>
            </table>
          </div>
        )}
      </div>
    </div>
  );
};

export default PatientsPage;
