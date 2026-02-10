Hospital Management System

A full-stack Hospital Management System designed with a strong focus on security, scalability, and real-world backend practices.
The application uses Spring Boot, Spring Security, JWT, and a modern React + TypeScript frontend to demonstrate production-grade authentication and role-based access control.

Overview

This project simulates a real hospital backend system where multiple user roles interact securely with protected resources. It implements stateless authentication, strict authorization rules, and a clean separation between backend services and frontend presentation.

Core Features:
Authentication & Authorization

JWT-based stateless authentication

Secure password hashing using BCrypt

Role-based access control:

ADMIN

DOCTOR

RECEPTIONIST

Method-level security using @PreAuthorize

Backend Capabilities

RESTful APIs for:

Department management

Doctor management

Patient management

Appointment scheduling

Billing and payments

DTO-driven architecture

MySQL persistence using JPA & Hibernate

Centralized exception handling

API validation and testing via Postman

Frontend Capabilities

React + TypeScript dashboard (Vite)

JWT-based authentication flow

Automatic token handling for API requests

Role-aware navigation and UI structure

Scalable layout for future analytics and reporting

Technology Stack

Backend

Java

Spring Boot

Spring Security

JWT

Hibernate / JPA

MySQL

Frontend

React

TypeScript

Vite

Tailwind CSS

Shadcn UI

Architecture Highlights

Stateless security design

Clean controller–service–repository separation

Secure backend–frontend communication

Production-oriented security configuration

Future Enhancements

Refresh token mechanism

Role-specific dashboards

Advanced reporting and analytics

Notification system (Email/SMS)

Dockerization and cloud deployment

Author

Akshat Rawat
Backend-focused Full Stack Developer
Learning through building real-world systems
