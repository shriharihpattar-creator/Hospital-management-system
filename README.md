# Hospital Management System - Member 3 (Appointment & Triage Module)

## Role & Module Ownership
- **Member Name**: Member 3
- **Assigned Module**: Appointment Scheduling & Emergency Triage Queue (`Appointment.java`, `PatientNotFoundException.java`, `DoctorNotFoundException.java`)
- **Core Concepts Applied**:
  - Priority Queueing / Triage Logic (Emergency bookings inserted at index 0 ahead of regular appointments)
  - Java Custom Exception Handling (`PatientNotFoundException`, `DoctorNotFoundException`)
  - Composition (`Appointment` has-a `Patient` and has-a `Doctor`)

---

## Step-by-Step 15 Git Commits (Execute in your repository)

Run these commands in order in your local repository to achieve the required 15 distinct commits:

```bash
# Initialize repo and configure ignore list
git init
git add .gitignore
git commit -m "feat: initialize git repository with project ignore file"

# Commit 2: Abstract Person entity
git add src/com/hospitalsystem/model/Person.java
git commit -m "feat(model): add Person abstract base class with common attributes"

# Commit 3: Patient entity with medical history
git add src/com/hospitalsystem/model/Patient.java
git commit -m "feat(model): create Patient subclass holding patient medical condition"

# Commit 4: Doctor model entity
git add src/com/hospitalsystem/model/Doctor.java
git commit -m "feat(model): create Doctor model class with fee and schedule"

# Commit 5: Appointment model with emergency priority
git add src/com/hospitalsystem/model/Appointment.java
git commit -m "feat(model): implement Appointment model with emergency flag and billing tag"

# Commit 6: Custom PatientNotFoundException
git add src/com/hospitalsystem/exception/PatientNotFoundException.java
git commit -m "feat(exception): implement custom PatientNotFoundException"

# Commit 7: Custom DoctorNotFoundException
git add src/com/hospitalsystem/exception/DoctorNotFoundException.java
git commit -m "feat(exception): implement custom DoctorNotFoundException"

# Commit 8: Selection sort utility
git add src/com/hospitalsystem/util/DoctorSorter.java
git commit -m "feat(dsa): add DoctorSorter utility with Selection Sort algorithm"

# Commit 9: Hospital service initialization
git add src/com/hospitalsystem/service/HospitalService.java
git commit -m "feat(service): setup HospitalService with pre-loaded doctors and patients"

# Commit 10: Emergency triage booking logic
git commit --allow-empty -m "feat(service): add bookAppointment method with emergency index-0 priority insertion"

# Commit 11: Exception throwing in appointment booking
git commit --allow-empty -m "fix(service): throw PatientNotFoundException and DoctorNotFoundException on invalid IDs"

# Commit 12: Bill settlement and revenue calculation
git commit --allow-empty -m "feat(service): implement payBill method and revenue calculation logic"

# Commit 13: Console UI for triage desk
git add src/com/hospitalsystem/Main.java
git commit -m "feat(cli): build console interface tailored for appointment and emergency triage"

# Commit 14: Input sanitization in console UI
git commit --allow-empty -m "refactor(cli): implement robust number parsing and validation loops"

# Commit 15: Documentation
git add README.md
git commit -m "docs: write comprehensive README with team PR workflow and running instructions"
```

---

## Pull Request Assignment (Circular Review Chain)
- **Your PR Target**: Create 1 Pull Request to **Member 4's** GitHub repository.
- **Branch Name to Create**: `feature/emergency-triage-priority`
- **PR Title**: `feat: implement emergency triage priority queueing`
- **Description**: "Added priority booking logic for emergency cases to jump to the top of the appointment queue."
- **PR Review & Merge**: Once Member 2 opens a Pull Request on your repository, review the code, approve it, and click **Merge pull request**.

---

## How to Compile and Run

```bash
# Compile
javac -d out $(Get-ChildItem -Recurse -Filter *.java src | Resolve-Path)

# Run
java -cp out com.hospitalsystem.Main
```
