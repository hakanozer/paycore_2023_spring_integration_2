package com.works.redisrepositories;

import com.works.models.RedisNote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;

@EnableRedisRepositories
public interface NoteRedisRepository extends JpaRepository<RedisNote, String > {

}
