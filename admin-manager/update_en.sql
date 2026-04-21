-- 更新部门表中的中文名称为英文
UPDATE sys_dept SET dept_name = 'Headquarters' WHERE dept_id = 1;
UPDATE sys_dept SET dept_name = 'Technical Department' WHERE dept_id = 2;
UPDATE sys_dept SET dept_name = 'Marketing Department' WHERE dept_id = 3;
UPDATE sys_dept SET dept_name = 'Finance Department' WHERE dept_id = 4;

-- 更新角色表中的中文名称为英文
UPDATE sys_role SET role_name = 'Super Administrator' WHERE role_id = 1;
UPDATE sys_role SET role_name = 'Administrator' WHERE role_id = 2;
UPDATE sys_role SET role_name = 'Operator' WHERE role_id = 3;

-- 更新菜单表中的中文名称为英文
UPDATE sys_menu SET menu_name = 'System Management' WHERE menu_id = 1;
UPDATE sys_menu SET menu_name = 'User Management' WHERE menu_id = 2;
UPDATE sys_menu SET menu_name = 'Role Management' WHERE menu_id = 3;
UPDATE sys_menu SET menu_name = 'Menu Management' WHERE menu_id = 4;
UPDATE sys_menu SET menu_name = 'Department Management' WHERE menu_id = 5;
UPDATE sys_menu SET menu_name = 'Post Management' WHERE menu_id = 6;
UPDATE sys_menu SET menu_name = 'Dictionary Management' WHERE menu_id = 7;
UPDATE sys_menu SET menu_name = 'Dictionary Type' WHERE menu_id = 8;
UPDATE sys_menu SET menu_name = 'Dictionary Data' WHERE menu_id = 9;
UPDATE sys_menu SET menu_name = 'Parameter Settings' WHERE menu_id = 10;
UPDATE sys_menu SET menu_name = 'Notice Management' WHERE menu_id = 11;
UPDATE sys_menu SET menu_name = 'System Monitoring' WHERE menu_id = 12;
UPDATE sys_menu SET menu_name = 'Online Users' WHERE menu_id = 13;
UPDATE sys_menu SET menu_name = 'Login Log' WHERE menu_id = 14;
UPDATE sys_menu SET menu_name = 'Operation Log' WHERE menu_id = 15;
UPDATE sys_menu SET menu_name = 'Server Monitoring' WHERE menu_id = 16;
UPDATE sys_menu SET menu_name = 'Cache Monitoring' WHERE menu_id = 17;

-- 更新字典类型表中的中文名称为英文
UPDATE sys_dict_type SET dict_name = 'User Status' WHERE dict_type = 'sys_user_status';
UPDATE sys_dict_type SET dict_name = 'Menu Type' WHERE dict_type = 'sys_menu_type';
UPDATE sys_dict_type SET dict_name = 'Role Status' WHERE dict_type = 'sys_role_status';
UPDATE sys_dict_type SET dict_name = 'Yes/No' WHERE dict_type = 'sys_yes_no';
UPDATE sys_dict_type SET dict_name = 'Notice Type' WHERE dict_type = 'sys_notice_type';
UPDATE sys_dict_type SET dict_name = 'Notice Status' WHERE dict_type = 'sys_notice_status';
UPDATE sys_dict_type SET dict_name = 'Operate Type' WHERE dict_type = 'sys_oper_type';

-- 更新字典数据表中的中文名称为英文
UPDATE sys_dict_data SET dict_label = 'Normal' WHERE dict_code = 1 AND dict_type = 'sys_user_status';
UPDATE sys_dict_data SET dict_label = 'Disabled' WHERE dict_code = 2 AND dict_type = 'sys_user_status';
UPDATE sys_dict_data SET dict_label = 'Catalog' WHERE dict_code = 1 AND dict_type = 'sys_menu_type';
UPDATE sys_dict_data SET dict_label = 'Menu' WHERE dict_code = 2 AND dict_type = 'sys_menu_type';
UPDATE sys_dict_data SET dict_label = 'Button' WHERE dict_code = 3 AND dict_type = 'sys_menu_type';
UPDATE sys_dict_data SET dict_label = 'Normal' WHERE dict_code = 1 AND dict_type = 'sys_role_status';
UPDATE sys_dict_data SET dict_label = 'Disabled' WHERE dict_code = 2 AND dict_type = 'sys_role_status';
UPDATE sys_dict_data SET dict_label = 'Yes' WHERE dict_code = 1 AND dict_type = 'sys_yes_no';
UPDATE sys_dict_data SET dict_label = 'No' WHERE dict_code = 2 AND dict_type = 'sys_yes_no';
UPDATE sys_dict_data SET dict_label = 'Notification' WHERE dict_code = 1 AND dict_type = 'sys_notice_type';
UPDATE sys_dict_data SET dict_label = 'Announcement' WHERE dict_code = 2 AND dict_type = 'sys_notice_type';
UPDATE sys_dict_data SET dict_label = 'Normal' WHERE dict_code = 1 AND dict_type = 'sys_notice_status';
UPDATE sys_dict_data SET dict_label = 'Disabled' WHERE dict_code = 2 AND dict_type = 'sys_notice_status';
UPDATE sys_dict_data SET dict_label = 'Add' WHERE dict_code = 1 AND dict_type = 'sys_oper_type';
UPDATE sys_dict_data SET dict_label = 'Modify' WHERE dict_code = 2 AND dict_type = 'sys_oper_type';
UPDATE sys_dict_data SET dict_label = 'Delete' WHERE dict_code = 3 AND dict_type = 'sys_oper_type';
UPDATE sys_dict_data SET dict_label = 'Query' WHERE dict_code = 4 AND dict_type = 'sys_oper_type';
UPDATE sys_dict_data SET dict_label = 'Import' WHERE dict_code = 5 AND dict_type = 'sys_oper_type';
UPDATE sys_dict_data SET dict_label = 'Export' WHERE dict_code = 6 AND dict_type = 'sys_oper_type';
UPDATE sys_dict_data SET dict_label = 'Login' WHERE dict_code = 7 AND dict_type = 'sys_oper_type';
UPDATE sys_dict_data SET dict_label = 'Logout' WHERE dict_code = 8 AND dict_type = 'sys_oper_type';

-- 更新配置表中的中文名称为英文
UPDATE sys_config SET config_name = 'System Name' WHERE config_key = 'sys.name';
UPDATE sys_config SET config_name = 'System Title' WHERE config_key = 'sys.title';
UPDATE sys_config SET config_name = 'System Logo' WHERE config_key = 'sys.logo';
UPDATE sys_config SET config_name = 'System Favicon' WHERE config_key = 'sys.favicon';
UPDATE sys_config SET config_name = 'User Registration' WHERE config_key = 'sys.account.registerUser';
UPDATE sys_config SET config_name = 'Captcha Enabled' WHERE config_key = 'sys.account.captchaEnabled';

-- 更新通知公告表中的中文标题为英文
UPDATE sys_notice SET notice_title = 'System Update Notification' WHERE notice_id = 1;
UPDATE sys_notice SET notice_title = 'Welcome to the System' WHERE notice_id = 2;