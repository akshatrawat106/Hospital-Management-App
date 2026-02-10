import { useState, useEffect } from "react";
import { departmentApi } from "@/lib/api";
import { DepartmentDTO } from "@/types";
import { Button } from "@/components/ui/button";
import { Input } from "@/components/ui/input";
import { Label } from "@/components/ui/label";
import { Dialog, DialogContent, DialogHeader, DialogTitle, DialogTrigger } from "@/components/ui/dialog";
import { Plus, Trash2, Edit2 } from "lucide-react";
import { toast } from "sonner";

const DepartmentsPage = () => {
  const [departments, setDepartments] = useState<DepartmentDTO[]>([]);
  const [loading, setLoading] = useState(false);
  const [dialogOpen, setDialogOpen] = useState(false);
  const [editDept, setEditDept] = useState<DepartmentDTO | null>(null);
  const [form, setForm] = useState<DepartmentDTO>({ dept_id: null, dept_name: "", location: "", dept_head: "" });

  const fetchData = async () => {
    setLoading(true);
    try {
      setDepartments(await departmentApi.getAll());
    } catch (err: any) {
      toast.error(err?.message || "Failed to load");
    } finally {
      setLoading(false);
    }
  };

  useEffect(() => { fetchData(); }, []);

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    try {
      if (editDept) {
        await departmentApi.update(editDept.dept_id!, form);
        toast.success("Department updated");
      } else {
        await departmentApi.register(form);
        toast.success("Department registered");
      }
      setDialogOpen(false);
      setEditDept(null);
      fetchData();
    } catch (err: any) {
      toast.error(err?.message || "Failed to save");
    }
  };

  const handleDelete = async (id: number) => {
    if (!confirm("Delete this department?")) return;
    try {
      await departmentApi.delete(id);
      toast.success("Department deleted");
      fetchData();
    } catch (err: any) {
      toast.error(err?.message || "Failed to delete");
    }
  };

  const openCreate = () => {
    setEditDept(null);
    setForm({ dept_id: null, dept_name: "", location: "", dept_head: "" });
    setDialogOpen(true);
  };

  const openEdit = (d: DepartmentDTO) => {
    setEditDept(d);
    setForm({ ...d });
    setDialogOpen(true);
  };

  return (
    <div>
      <div className="page-header flex flex-col sm:flex-row sm:items-center sm:justify-between gap-4">
        <div>
          <h1 className="page-title">Departments</h1>
          <p className="page-subtitle">Manage hospital departments</p>
        </div>
        <Dialog open={dialogOpen} onOpenChange={setDialogOpen}>
          <DialogTrigger asChild>
            <Button onClick={openCreate}><Plus className="w-4 h-4 mr-2" /> Add Department</Button>
          </DialogTrigger>
          <DialogContent>
            <DialogHeader>
              <DialogTitle>{editDept ? "Edit Department" : "Add Department"}</DialogTitle>
            </DialogHeader>
            <form onSubmit={handleSubmit} className="space-y-4">
              <div className="space-y-2"><Label>Department Name</Label><Input value={form.dept_name} onChange={(e) => setForm({ ...form, dept_name: e.target.value })} required /></div>
              <div className="space-y-2"><Label>Location</Label><Input value={form.location} onChange={(e) => setForm({ ...form, location: e.target.value })} required /></div>
              <div className="space-y-2"><Label>Department Head</Label><Input value={form.dept_head} onChange={(e) => setForm({ ...form, dept_head: e.target.value })} required /></div>
              <Button type="submit" className="w-full">{editDept ? "Update" : "Register"}</Button>
            </form>
          </DialogContent>
        </Dialog>
      </div>

      <div className="grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-3 gap-5">
        {loading ? (
          <div className="col-span-full p-12 text-center text-muted-foreground">Loading...</div>
        ) : departments.length === 0 ? (
          <div className="col-span-full p-12 text-center text-muted-foreground">No departments found.</div>
        ) : (
          departments.map((d) => (
            <div key={d.dept_id} className="stat-card">
              <div className="flex items-start justify-between mb-3">
                <h3 className="text-lg font-display font-semibold text-foreground">{d.dept_name}</h3>
                <div className="flex gap-1">
                  <Button variant="ghost" size="icon" className="h-8 w-8" onClick={() => openEdit(d)}>
                    <Edit2 className="h-3.5 w-3.5" />
                  </Button>
                  <Button variant="ghost" size="icon" className="h-8 w-8 text-destructive hover:text-destructive" onClick={() => handleDelete(d.dept_id!)}>
                    <Trash2 className="h-3.5 w-3.5" />
                  </Button>
                </div>
              </div>
              <p className="text-sm text-muted-foreground">📍 {d.location}</p>
              <p className="text-sm text-muted-foreground">👤 Head: {d.dept_head}</p>
            </div>
          ))
        )}
      </div>
    </div>
  );
};

export default DepartmentsPage;
