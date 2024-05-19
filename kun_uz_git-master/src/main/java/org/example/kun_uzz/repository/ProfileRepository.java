package org.example.kun_uzz.repository;

import org.example.kun_uzz.Entity.ProfileEntity;
import org.example.kun_uzz.Entity.RegionEntity;
import org.springframework.data.repository.CrudRepository;

public interface ProfileRepository extends CrudRepository<ProfileEntity, Integer> {
}
