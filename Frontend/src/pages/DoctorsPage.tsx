import { useState, useEffect } from "react";
import { doctorApi, departmentApi } from "@/lib/api";
import { DoctorDTO, DepartmentDTO } from "@/types";
import { Button } from "@/components/ui/button";
import { Input } from "@/components/ui/input";
import { Label } from "@/components/ui/label";
import { Dialog, DialogContent, DialogHeader, DialogTitle, DialogTrigger } from "@/components/ui/dialog";
import { Select, SelectContent, SelectItem, SelectTrigger, SelectValue } from "@/components/ui/select";
import { Plus, Search, Trash2, Edit2, RefreshCw } from "lucide-react";
import { toast } from "sonner";

const DoctorsPage = () => {
  const [doctors, setDoctors] = useState<DoctorDTO[]>([]);
  const [departments, setDepartments] = useState<DepartmentDTO[]>([]);
  const [loading, setLoading] = useState(false);
  const [search, setSearch] = useState("");
  const [dialogOpen, setDialogOpen] = useState(false);
  const [editDoctor, setEditDoctor] = useState<DoctorDTO | null>(null);
  const [form, setForm] = useState<DoctorDTO>({
    doctorId: null, name: "", specialization: "", contact_no: "", email_id: "", departmentId: 0, availability: true,
  });

  const fetchData = async () => {
    setLoading(true);
    try {
      const [docs, deps] = await Promise.all([doctorApi.getAll(), departmentApi.getAll()]);
      setDoctors(docs);
      setDepartments(deps);
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
      if (editDoctor) {
        await doctorApi.update(editDoctor.doctorId!, form);
        toast.success("Doctor updated");
      } else {
        await doctorApi.register(form);
        toast.success("Doctor registered");
      }
      setDialogOpen(false);
      setEditDoctor(null);
      fetchData();
    } catch (err: any) {
      toast.error(err?.message || "Failed to save");
    }
  };

  const handleDelete = async (id: number) => {
    if (!confirm("Delete this doctor?")) return;
    try {
      await doctorApi.delete(id);
      toast.success("Doctor deleted");
      fetchData();
    } catch (err: any) {
      toast.error(err?.message || "Failed to delete");
    }
  };

  const toggleAvailability = async (doc: DoctorDTO) => {
    try {
      await doctorApi.setAvailability(doc.doctorId!, !doc.availability);
      toast.success("Availability updated");
      fetchData();
    } catch (err: any) {
      toast.error(err?.message || "Failed to update");
    }
  };

  const openCreate = () => {
    setEditDoctor(null);
    setForm({ doctorId: null, name: "", specialization: "", contact_no: "", email_id: "", departmentId: 0, availability: true });
    setDialogOpen(true);
  };

  const openEdit = (doc: DoctorDTO) => {
    setEditDoctor(doc);
    setForm({ ...doc });
    setDialogOpen(true);
  };

  const filtered = doctors.filter((d) =>
    d.name.toLowerCase().includes(search.toLowerCase()) ||
    d.specialization.toLowerCase().includes(search.toLowerCase())
  );

  return (
    <div>
      <div className="page-header flex flex-col sm:flex-row sm:items-center sm:justify-between gap-4">
        <div>
          <h1 className="page-title">Doctors</h1>
          <p className="page-subtitle">Manage doctor profiles and availability</p>
        </div>
        <div className="flex items-center gap-3">
          <div className="relative">
            <Search className="absolute left-3 top-1/2 -translate-y-1/2 h-4 w-4 text-muted-foreground" />
            <Input placeholder="Search doctors..." value={search} onChange={(e) => setSearch(e.target.value)} className="pl-9 w-56" />
          </div>
          <Dialog open={dialogOpen} onOpenChange={setDialogOpen}>
            <DialogTrigger asChild>
              <Button onClick={openCreate}><Plus className="w-4 h-4 mr-2" /> Add Doctor</Button>
            </DialogTrigger>
            <DialogContent>
              <DialogHeader>
                <DialogTitle>{editDoctor ? "Edit Doctor" : "Register Doctor"}</DialogTitle>
              </DialogHeader>
              <form onSubmit={handleSubmit} className="space-y-4">
                <div className="grid grid-cols-2 gap-4">
                  <div className="space-y-2">
                    <Label>Name</Label>
                    <Input value={form.name} onChange={(e) => setForm({ ...form, name: e.target.value })} required />
                  </div>
                  <div className="space-y-2">
                    <Label>Specialization</Label>
                    <Input value={form.specialization} onChange={(e) => setForm({ ...form, specialization: e.target.value })} required />
                  </div>
                  <div className="space-y-2">
                    <Label>Contact No</Label>
                    <Input value={form.contact_no} onChange={(e) => setForm({ ...form, contact_no: e.target.value })} required />
                  </div>
                  <div className="space-y-2">
                    <Label>Email</Label>
                    <Input type="email" value={form.email_id} onChange={(e) => setForm({ ...form, email_id: e.target.value })} required />
                  </div>
                </div>
                <div className="space-y-2">
                  <Label>Department</Label>
                  <Select value={String(form.departmentId || "")} onValueChange={(v) => setForm({ ...form, departmentId: Number(v) })}>
                    <SelectTrigger><SelectValue placeholder="Select department" /></SelectTrigger>
                    <SelectContent>
                      {departments.map((d) => (
                        <SelectItem key={d.dept_id} value={String(d.dept_id)}>{d.dept_name}</SelectItem>
                      ))}
                    </SelectContent>
                  </Select>
                </div>
                <Button type="submit" className="w-full">{editDoctor ? "Update" : "Register"}</Button>
              </form>
            </DialogContent>
          </Dialog>
        </div>
      </div>

      <div className="bg-card rounded-xl border border-border overflow-hidden">
        {loading ? (
          <div className="p-12 text-center text-muted-foreground">Loading...</div>
        ) : filtered.length === 0 ? (
          <div className="p-12 text-center text-muted-foreground">
            No doctors found. {doctors.length === 0 && "Make sure your backend is running."}
          </div>
        ) : (
          <div className="overflow-x-auto">
            <table className="data-table">
              <thead>
                <tr>
                  <th>ID</th>
                  <th>Name</th>
                  <th>Specialization</th>
                  <th>Contact</th>
                  <th>Email</th>
                  <th>Availability</th>
                  <th>Actions</th>
                </tr>
              </thead>
              <tbody>
                {filtered.map((doc) => (
                  <tr key={doc.doctorId}>
                    <td className="font-medium">{doc.doctorId}</td>
                    <td className="font-medium">{doc.name}</td>
                    <td>{doc.specialization}</td>
                    <td>{doc.contact_no}</td>
                    <td>{doc.email_id}</td>
                    <td>
                      <button onClick={() => toggleAvailability(doc)} className={doc.availability ? "badge-available" : "badge-unavailable"}>
                        {doc.availability ? "Available" : "Unavailable"}
                      </button>
                    </td>
                    <td>
                      <div className="flex items-center gap-1">
                        <Button variant="ghost" size="icon" onClick={() => openEdit(doc)} className="h-8 w-8">
                          <Edit2 className="h-3.5 w-3.5" />
                        </Button>
                        <Button variant="ghost" size="icon" onClick={() => handleDelete(doc.doctorId!)} className="h-8 w-8 text-destructive hover:text-destructive">
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

export default DoctorsPage;
