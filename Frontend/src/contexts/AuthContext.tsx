import React, { createContext, useContext, useState, useCallback, useEffect } from "react";
import { AuthUser, LoginRequest } from "@/types";
import { authApi } from "@/lib/api";

interface AuthContextType {
  user: AuthUser | null;
  isAuthenticated: boolean;
  login: (data: LoginRequest) => Promise<void>;
  logout: () => void;
  loading: boolean;
}

const AuthContext = createContext<AuthContextType | undefined>(undefined);

function parseJwt(token: string): { sub: string; roles: string[]; exp: number } | null {
  try {
    const base64Url = token.split(".")[1];
    const base64 = base64Url.replace(/-/g, "+").replace(/_/g, "/");
    const jsonPayload = decodeURIComponent(
      atob(base64)
        .split("")
        .map((c) => "%" + ("00" + c.charCodeAt(0).toString(16)).slice(-2))
        .join("")
    );
    return JSON.parse(jsonPayload);
  } catch {
    return null;
  }
}

export function AuthProvider({ children }: { children: React.ReactNode }) {
  const [user, setUser] = useState<AuthUser | null>(null);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    const stored = localStorage.getItem("hospital_auth");
    if (stored) {
      try {
        const parsed = JSON.parse(stored) as AuthUser;
        const jwt = parseJwt(parsed.token);
        if (jwt && jwt.exp * 1000 > Date.now()) {
          setUser(parsed);
        } else {
          localStorage.removeItem("hospital_auth");
        }
      } catch {
        localStorage.removeItem("hospital_auth");
      }
    }
    setLoading(false);
  }, []);

  const login = useCallback(async (data: LoginRequest) => {
    const response = await authApi.login(data);
    const jwt = parseJwt(response.token);
    const role = jwt?.roles?.[0]?.replace("ROLE_", "") || "ADMIN";
    const authUser: AuthUser = {
      username: data.username,
      role,
      token: response.token,
    };
    localStorage.setItem("hospital_auth", JSON.stringify(authUser));
    setUser(authUser);
  }, []);

  const logout = useCallback(() => {
    localStorage.removeItem("hospital_auth");
    setUser(null);
  }, []);

  return (
    <AuthContext.Provider value={{ user, isAuthenticated: !!user, login, logout, loading }}>
      {children}
    </AuthContext.Provider>
  );
}

export function useAuth() {
  const context = useContext(AuthContext);
  if (!context) throw new Error("useAuth must be used within AuthProvider");
  return context;
}
