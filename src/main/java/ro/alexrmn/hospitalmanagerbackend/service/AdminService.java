package ro.alexrmn.hospitalmanagerbackend.service;

import ro.alexrmn.hospitalmanagerbackend.model.Admin;
import ro.alexrmn.hospitalmanagerbackend.model.dto.AdminDto;
import ro.alexrmn.hospitalmanagerbackend.model.dto.CreateAdminDto;

import java.util.List;

public interface AdminService {

    AdminDto saveAdmin(CreateAdminDto createAdminDto);

    AdminDto getAdmin(Long id);

    List<AdminDto> getAdmins();

    AdminDto updateAdmin(Long id, AdminDto adminDto);

    void deleteAdmin(Long id);

}
