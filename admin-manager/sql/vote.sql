DROP TABLE IF EXISTS `qq_candidate`;
CREATE TABLE `qq_candidate`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) ,
  `img_path` varchar(128),
  `desc` varchar(256),
  create_by varchar(50) ,
  create_time datetime,
  update_by varchar(50) ,
  update_time datetime,
  PRIMARY KEY (`id`) USING BTREE

  
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;


DROP TABLE IF EXISTS `qq_enc_vote`;
CREATE TABLE `qq_enc_vote`  (
  `id` varchar(64) NOT NULL ,
  `user_id` varchar(50) ,
  `candidate_id` bigint(20),
  `user_name` varchar(256),
  candidate_name varchar(50) ,
  vote_ballot_record varchar(1024) ,
  vote_plain_record varchar(128) ,
  vote_time datetime,
  create_by varchar(50) ,
  create_time datetime,
  update_by varchar(50) ,
  update_time datetime,
  PRIMARY KEY (`id`) USING BTREE

  
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;