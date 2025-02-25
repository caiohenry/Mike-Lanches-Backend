package br.com.mike_lanches.api.apps.user.models;

// Imports
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.LocalDate;
import java.util.Collection;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;

// Create model - users
@Entity
@Table(name = "users")
@Getter
@Setter
public class UserModel implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Name
    @Column(name = "user_name", length = 255, nullable = false, unique = false)
    @JsonProperty("user_name")
    private String name;

    // Email
    @Column(name = "user_email", length = 255, nullable = false, unique = true)
    @JsonProperty("user_email")
    private String email;

    // Password
    @Column(name = "user_password", length = 255, nullable = false, unique = false)
    @JsonProperty("user_password")
    private String password;

    // Phone
    @Column(name = "user_phone", length = 15, nullable = false, unique = false)
    @JsonProperty("user_phone")
    private String phone;

    // Status
    @Column(name = "user_status", nullable = false)
    @JsonProperty("user_status")
    private boolean status;

    // Removed Date
    @Column(name = "user_removed_date", nullable = true)
    @JsonProperty("user_removed_date")
    private LocalDate removedDate;

    // Date Joined
    @Column(name = "user_date_joined", nullable = false)
    @JsonProperty("user_date_joined")
    private LocalDate dateJoined;

    // Is Staff
    @Column(name = "user_is_staff", nullable = false)
    @JsonProperty("user_is_staff")
    private boolean isStaff;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
