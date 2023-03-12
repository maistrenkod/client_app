package one.maistrenko.client_app;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageDescriptionRepository extends JpaRepository <MessageDescription, Long> {

    @Override
    <S extends MessageDescription> S saveAndFlush(S entity);
}
