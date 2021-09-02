package az.millikart.boktadminpanelrestbe.security.jwt;

import az.millikart.boktadminpanelrestbe.entity.RoleEntity;
import az.millikart.boktadminpanelrestbe.entity.Status;
import az.millikart.boktadminpanelrestbe.entity.UserLoginEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Slf4j
public final class JwtUserFactory {

    public JwtUserFactory() {
    }

    public static JwtUser create(UserLoginEntity userLoginEntity) {

        return JwtUser.builder()
                .id(userLoginEntity.getId())
                .username(userLoginEntity.getUsername())
                .password(userLoginEntity.getPassword())
                .authorities(getGrantedAuthorityList(new ArrayList<>(userLoginEntity
                        .getRoles())))
                .enabled(userLoginEntity.getStatus().equals(Status.ACTIVE))
                .build();
    }

    private static List<GrantedAuthority> getGrantedAuthorityList(List<RoleEntity> userRoleEntities) {

        log.info("get granted authority list: " + userRoleEntities.get(0).getName());

        return userRoleEntities.stream()
                .map(role ->
                        new SimpleGrantedAuthority(role.getName())
                ).collect(Collectors.toList());
    }

//    public static void main(String[] args) {
//        System.out.println("test");
//        UserLoginEntity userLoginEntity = new UserLoginEntity();
//        userLoginEntity.setUsername("testuser");
//        userLoginEntity.setPassword("12345");
//        userLoginEntity.setStatus(Status.ACTIVE);
//        userLoginEntity.setId(1l);
//        userLoginEntity.setRoles(null);
//        List<RoleEntity> roleEntities = new ArrayList<RoleEntity>();
//        RoleEntity roleEntity = new RoleEntity();
//        roleEntity.setId(1l);
//        roleEntity.setName("operator");
//        roleEntities.add(roleEntity);
//        userLoginEntity.setRoles(roleEntities);
//        JwtUser jwtUser = JwtUserFactory.create(userLoginEntity);
//
////        System.out.println(
//        System.out.println(JwtUserFactory.create(userLoginEntity));
////        );
//    }

}
