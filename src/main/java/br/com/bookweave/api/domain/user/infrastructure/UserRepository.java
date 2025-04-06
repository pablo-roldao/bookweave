package br.com.bookweave.api.domain.user.infrastructure;

import br.com.bookweave.api.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
