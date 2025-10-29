# TODO: Implement Role-Based Access by Merging User and Donor Entities

## Step 1: Merge Entities
- [x] Update Donor.java: Add username, role (default "DONOR"), enabled. Rename 'name' to 'username'. Change table name to "donors".
- [x] Remove User.java.

## Step 2: Update Repositories/Services
- [x] Update DonorRepository.java: Add auth methods (findByUsername, existsByUsername).
- [x] Update DonorService.java: Add registration logic and password encoding.

## Step 3: Implement UserDetailsService
- [x] Create UserDetailsServiceImpl.java: Load users by username from DonorRepository.

## Step 4: Update SecurityConfig
- [x] Update SecurityConfig.java: Inject UserDetailsService, add role-based authorization, and success handler for role-based redirects.

## Step 5: Update Controllers
- [x] Update UserController.java: Change to use Donor.
- [x] Update HomeController.java: Add role-based redirects.
- [x] Create AdminController.java: For /admin/dashboard.
- [x] Create DonorDashboardController.java: For /donor/dashboard.

## Step 6: Restrict Access
- [x] Update DonorController.java: Add @PreAuthorize for role-based access.
- [x] Update DonationController.java: Add @PreAuthorize for role-based access.

## Step 7: Update Signup
- [x] Update registration in DonorService/UserController: Set default role to "DONOR".

## Step 8: Update Templates
- [x] Create admin-dashboard.html.
- [x] Create donor-dashboard.html.
- [x] Update index.html: Add role-based links.
- [ ] Update navigation in templates with sec:authorize for roles.
- [ ] Update donors.html, donations.html for role-based UI.

## Followup Steps
- [ ] Update database schema (rename table, add columns).
- [ ] Test role-based access and redirects.
- [ ] Handle data migration if needed.
