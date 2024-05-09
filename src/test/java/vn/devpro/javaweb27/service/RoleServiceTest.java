package vn.devpro.javaweb27.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import vn.devpro.javaweb27.model.Role;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class RoleServiceTest {

    @InjectMocks
    private RoleService roleService;

    @Mock
    private EntityManager entityManager;

    @Mock
    private Query query;

    @SuppressWarnings("deprecation")
	@Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testFindAllActive() {
        // Given
        List<Role> roles = new ArrayList<>();
        roles.add(new Role());
        when(entityManager.createNativeQuery(anyString(), eq(Role.class))).thenReturn(query);
        when(query.getResultList()).thenReturn(roles);

        // When
        List<Role> result = roleService.findAllActive();

        // Then
        assertEquals(1, result.size());
    }

    @Test
    public void testDeleteRoleById() {
        // Given
        int roleId = 1;

        // When
        roleService.deleteRoleById(roleId);

        // Then
        // Verify if the method is invoked without exception
    }

    @Test
    public void testGetRoleByName() {
        // Given
        String roleName = "ROLE_USER";
        List<Role> roles = new ArrayList<>();
        Role role = new Role();
        role.setName(roleName);
        roles.add(role);
        when(entityManager.createNativeQuery(anyString(), eq(Role.class))).thenReturn(query);
        when(query.getResultList()).thenReturn(roles);

        // When
        Role result = roleService.getRoleByName(roleName);

        // Then
        assertEquals(roleName, result.getName());
    }

    // Add more test cases for other methods
}
