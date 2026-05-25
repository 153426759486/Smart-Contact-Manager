package com.scm30.repositories;

import com.scm30.entity.Contact;
import com.scm30.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface ContactRepo extends JpaRepository<Contact,String> {
    Page<Contact> findByUser(User user, Pageable pageable);

    @Query("Select c from Contact c where c.user.Id= :userId")
    List<Contact> findByUserId(@Param("userId") String userId);
}
