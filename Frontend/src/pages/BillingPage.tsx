import { useState } from "react";
import { billingApi } from "@/lib/api";
import { BillingDTO, BillingReqDTO } from "@/types";
import { Button } from "@/components/ui/button";
import { Input } from "@/components/ui/input";
import { Label } from "@/components/ui/label";
import { Tabs, TabsContent, TabsList, TabsTrigger } from "@/components/ui/tabs";
import { Receipt, Search, CreditCard } from "lucide-react";
import { toast } from "sonner";

const BillingPage = () => {
  // Create bill
  const [billForm, setBillForm] = useState<BillingReqDTO>({
    patientId: 0, billDate: "", roomCharges: 0, medicineCharges: 0, doctorCharges: 0,
  });
  const [creating, setCreating] = useState(false);

  // Search
  const [searchType, setSearchType] = useState<"id" | "name" | "patientId">("id");
  const [searchValue, setSearchValue] = useState("");
  const [bills, setBills] = useState<BillingDTO[]>([]);
  const [searching, setSearching] = useState(false);

  const handleCreateBill = async (e: React.FormEvent) => {
    e.preventDefault();
    setCreating(true);
    try {
      const bill = await billingApi.create(billForm);
      toast.success(`Bill #${bill.billId} created — Total: ₹${bill.totalCharges}`);
    } catch (err: any) {
      toast.error(err?.message || "Failed to create bill");
    } finally {
      setCreating(false);
    }
  };

  const handleSearch = async () => {
    if (!searchValue.trim()) return;
    setSearching(true);
    try {
      let result: BillingDTO[] = [];
      if (searchType === "id") {
        const bill = await billingApi.getById(Number(searchValue));
        result = [bill];
      } else if (searchType === "name") {
        result = await billingApi.getByPatientName(searchValue);
      } else {
        result = await billingApi.getByPatientId(Number(searchValue));
      }
      setBills(result);
      if (result.length === 0) toast.info("No bills found");
    } catch (err: any) {
      toast.error(err?.message || "Search failed");
      setBills([]);
    } finally {
      setSearching(false);
    }
  };

  const handlePayBill = async (billId: number) => {
    try {
      await billingApi.pay(billId);
      toast.success("Bill paid successfully");
      setBills((prev) =>
        prev.map((b) => (b.billId === billId ? { ...b, status: true } : b))
      );
    } catch (err: any) {
      toast.error(err?.message || "Payment failed");
    }
  };

  return (
    <div>
      <div className="page-header">
        <h1 className="page-title">Billing</h1>
        <p className="page-subtitle">Create and manage patient bills</p>
      </div>

      <Tabs defaultValue="create" className="max-w-3xl">
        <TabsList>
          <TabsTrigger value="create">Create Bill</TabsTrigger>
          <TabsTrigger value="search">Search Bills</TabsTrigger>
        </TabsList>

        <TabsContent value="create">
          <div className="bg-card rounded-xl border border-border p-6 mt-4">
            <div className="flex items-center gap-3 mb-6">
              <div className="w-10 h-10 rounded-lg bg-primary/10 flex items-center justify-center">
                <Receipt className="w-5 h-5 text-primary" />
              </div>
              <h2 className="text-lg font-display font-semibold">New Bill</h2>
            </div>
            <form onSubmit={handleCreateBill} className="space-y-4">
              <div className="grid grid-cols-2 gap-4">
                <div className="space-y-2">
                  <Label>Patient ID</Label>
                  <Input type="number" value={billForm.patientId || ""} onChange={(e) => setBillForm({ ...billForm, patientId: Number(e.target.value) })} required />
                </div>
                <div className="space-y-2">
                  <Label>Bill Date</Label>
                  <Input type="datetime-local" value={billForm.billDate} onChange={(e) => setBillForm({ ...billForm, billDate: e.target.value })} required />
                </div>
                <div className="space-y-2">
                  <Label>Room Charges (₹)</Label>
                  <Input type="number" step="0.01" value={billForm.roomCharges || ""} onChange={(e) => setBillForm({ ...billForm, roomCharges: Number(e.target.value) })} />
                </div>
                <div className="space-y-2">
                  <Label>Medicine Charges (₹)</Label>
                  <Input type="number" step="0.01" value={billForm.medicineCharges || ""} onChange={(e) => setBillForm({ ...billForm, medicineCharges: Number(e.target.value) })} />
                </div>
                <div className="space-y-2">
                  <Label>Doctor Charges (₹)</Label>
                  <Input type="number" step="0.01" value={billForm.doctorCharges || ""} onChange={(e) => setBillForm({ ...billForm, doctorCharges: Number(e.target.value) })} />
                </div>
              </div>
              <Button type="submit" className="w-full" disabled={creating}>
                {creating ? "Creating..." : "Create Bill"}
              </Button>
            </form>
          </div>
        </TabsContent>

        <TabsContent value="search">
          <div className="bg-card rounded-xl border border-border p-6 mt-4">
            <div className="flex items-center gap-3 mb-6">
              <div className="w-10 h-10 rounded-lg bg-primary/10 flex items-center justify-center">
                <Search className="w-5 h-5 text-primary" />
              </div>
              <h2 className="text-lg font-display font-semibold">Search Bills</h2>
            </div>
            <div className="flex gap-3 mb-4">
              <select
                value={searchType}
                onChange={(e) => setSearchType(e.target.value as any)}
                className="border border-input rounded-md px-3 py-2 text-sm bg-background"
              >
                <option value="id">Bill ID</option>
                <option value="name">Patient Name</option>
                <option value="patientId">Patient ID</option>
              </select>
              <Input
                placeholder={searchType === "name" ? "Enter patient name..." : "Enter ID..."}
                value={searchValue}
                onChange={(e) => setSearchValue(e.target.value)}
                className="flex-1"
              />
              <Button onClick={handleSearch} disabled={searching}>
                {searching ? "Searching..." : "Search"}
              </Button>
            </div>

            {bills.length > 0 && (
              <div className="overflow-x-auto">
                <table className="data-table">
                  <thead>
                    <tr><th>Bill ID</th><th>Patient</th><th>Date</th><th>Total (₹)</th><th>Status</th><th>Action</th></tr>
                  </thead>
                  <tbody>
                    {bills.map((b) => (
                      <tr key={b.billId}>
                        <td className="font-medium">{b.billId}</td>
                        <td>{b.patientname}</td>
                        <td>{b.billDate ? new Date(b.billDate).toLocaleDateString() : "—"}</td>
                        <td className="font-semibold">₹{b.totalCharges?.toLocaleString()}</td>
                        <td>
                          <span className={b.status ? "badge-paid" : "badge-unpaid"}>
                            {b.status ? "Paid" : "Unpaid"}
                          </span>
                        </td>
                        <td>
                          {!b.status && (
                            <Button size="sm" variant="outline" onClick={() => handlePayBill(b.billId!)}>
                              <CreditCard className="w-3.5 h-3.5 mr-1" /> Pay
                            </Button>
                          )}
                        </td>
                      </tr>
                    ))}
                  </tbody>
                </table>
              </div>
            )}
          </div>
        </TabsContent>
      </Tabs>
    </div>
  );
};

export default BillingPage;
