-- 为sys_user表添加辅助查询列，支持LIKE查询
ALTER TABLE sys_user ADD COLUMN phonenumber_like VARCHAR(64) COMMENT '手机号辅助查询列';

-- 更新现有数据，将加密的手机号复制到辅助列
UPDATE sys_user SET phonenumber_like = phonenumber WHERE phonenumber IS NOT NULL;

-- 为辅助查询列创建索引，提高查询性能
CREATE INDEX idx_phonenumber_like ON sys_user(phonenumber_like);