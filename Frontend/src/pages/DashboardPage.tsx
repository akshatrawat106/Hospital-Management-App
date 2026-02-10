import { useAuth } from "@/contexts/AuthContext";
import { Users, Stethoscope, Building2, CalendarCheck, Receipt, TrendingUp } from "lucide-react";

const DashboardPage = () => {
  const { user } = useAuth();

  const stats = [
    { label: "Doctors", icon: Stethoscope, value: "—", color: "text-primary" },
    { label: "Patients", icon: Users, value: "—", color: "text-info" },
    { label: "Departments", icon: Building2, value: "—", color: "text-warning" },
    { label: "Appointments", icon: CalendarCheck, value: "—", color: "text-success" },
    { label: "Pending Bills", icon: Receipt, value: "—", color: "text-destructive" },
    { label: "Revenue", icon: TrendingUp, value: "—", color: "text-primary" },
  ];

  return (
    <div>
      <div className="page-header">
        <h1 className="page-title">Dashboard</h1>
        <p className="page-subtitle">Welcome back, {user?.username}. Here's an overview of your hospital.</p>
      </div>

      <div className="grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-3 gap-5 mb-8">
        {stats.map((stat) => (
          <div key={stat.label} className="stat-card">
            <div className="flex items-center justify-between mb-3">
              <span className="text-sm font-medium text-muted-foreground">{stat.label}</span>
              <stat.icon className={`w-5 h-5 ${stat.color}`} />
            </div>
            <p className="text-3xl font-display font-bold text-foreground">{stat.value}</p>
            <p className="text-xs text-muted-foreground mt-1">Connect backend to see live data</p>
          </div>
        ))}
      </div>

      <div className="stat-card">
        <h3 className="text-lg font-display font-semibold text-foreground mb-2">Getting Started</h3>
        <p className="text-sm text-muted-foreground leading-relaxed">
          This dashboard connects to your Spring Boot backend at <code className="text-xs bg-muted px-1.5 py-0.5 rounded">http://localhost:8080</code>. 
          Make sure your backend is running, then use the sidebar to manage doctors, patients, departments, appointments, and billing. 
          All API calls include your JWT token automatically.
        </p>
      </div>
    </div>
  );
};

export default DashboardPage;
