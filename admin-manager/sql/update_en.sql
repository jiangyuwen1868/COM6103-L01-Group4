-- 更新部门表中的部门名称
UPDATE sys_dept SET dept_name = 'RuoYi Technology' WHERE dept_id = 100;
UPDATE sys_dept SET dept_name = 'Shenzhen Headquarters' WHERE dept_id = 101;
UPDATE sys_dept SET dept_name = 'Changsha Branch' WHERE dept_id = 102;
UPDATE sys_dept SET dept_name = 'R&D Department' WHERE dept_id = 103;
UPDATE sys_dept SET dept_name = 'Marketing Department' WHERE dept_id = 104;
UPDATE sys_dept SET dept_name = 'Testing Department' WHERE dept_id = 105;
UPDATE sys_dept SET dept_name = 'Finance Department' WHERE dept_id = 106;
UPDATE sys_dept SET dept_name = 'Operation Department' WHERE dept_id = 107;
UPDATE sys_dept SET dept_name = 'Marketing Department' WHERE dept_id = 108;
UPDATE sys_dept SET dept_name = 'Finance Department' WHERE dept_id = 109;

-- 更新用户表中的用户昵称和备注
UPDATE sys_user SET user_name = 'RuoYi', remark = 'Administrator' WHERE user_id = 1;
UPDATE sys_user SET user_name = 'RuoYi', remark = 'Tester' WHERE user_id = 2;

-- 更新岗位表中的岗位名称
UPDATE sys_post SET post_name = 'CEO' WHERE post_id = 1;
UPDATE sys_post SET post_name = 'Project Manager' WHERE post_id = 2;
UPDATE sys_post SET post_name = 'Human Resources' WHERE post_id = 3;
UPDATE sys_post SET post_name = 'Staff' WHERE post_id = 4;

-- 更新角色表中的角色名称和备注
UPDATE sys_role SET role_name = 'Super Administrator', remark = 'Super Administrator' WHERE role_id = 1;
UPDATE sys_role SET role_name = 'Common Role', remark = 'Common Role' WHERE role_id = 2;

-- 更新菜单表中的菜单名称和备注
-- 一级菜单
UPDATE sys_menu SET menu_name = 'System Management', remark = 'System Management Directory' WHERE menu_id = 1;
UPDATE sys_menu SET menu_name = 'System Monitoring', remark = 'System Monitoring Directory' WHERE menu_id = 2;
UPDATE sys_menu SET menu_name = 'System Tools', remark = 'System Tools Directory' WHERE menu_id = 3;
UPDATE sys_menu SET menu_name = 'RuoYi Official Website', remark = 'RuoYi Official Website Address' WHERE menu_id = 4;
-- 二级菜单
UPDATE sys_menu SET menu_name = 'User Management', remark = 'User Management Menu' WHERE menu_id = 100;
UPDATE sys_menu SET menu_name = 'Role Management', remark = 'Role Management Menu' WHERE menu_id = 101;
UPDATE sys_menu SET menu_name = 'Menu Management', remark = 'Menu Management Menu' WHERE menu_id = 102;
UPDATE sys_menu SET menu_name = 'Department Management', remark = 'Department Management Menu' WHERE menu_id = 103;
UPDATE sys_menu SET menu_name = 'Post Management', remark = 'Post Management Menu' WHERE menu_id = 104;
UPDATE sys_menu SET menu_name = 'Dictionary Management', remark = 'Dictionary Management Menu' WHERE menu_id = 105;
UPDATE sys_menu SET menu_name = 'Parameter Settings', remark = 'Parameter Settings Menu' WHERE menu_id = 106;
UPDATE sys_menu SET menu_name = 'Notice Management', remark = 'Notice Management Menu' WHERE menu_id = 107;
UPDATE sys_menu SET menu_name = 'Log Management', remark = 'Log Management Menu' WHERE menu_id = 108;
UPDATE sys_menu SET menu_name = 'Online Users', remark = 'Online Users Menu' WHERE menu_id = 109;
UPDATE sys_menu SET menu_name = 'Scheduled Tasks', remark = 'Scheduled Tasks Menu' WHERE menu_id = 110;
UPDATE sys_menu SET menu_name = 'Data Monitoring', remark = 'Data Monitoring Menu' WHERE menu_id = 111;
UPDATE sys_menu SET menu_name = 'Server Monitoring', remark = 'Server Monitoring Menu' WHERE menu_id = 112;
UPDATE sys_menu SET menu_name = 'Cache Monitoring', remark = 'Cache Monitoring Menu' WHERE menu_id = 113;
UPDATE sys_menu SET menu_name = 'Form Builder', remark = 'Form Builder Menu' WHERE menu_id = 114;
UPDATE sys_menu SET menu_name = 'Code Generator', remark = 'Code Generator Menu' WHERE menu_id = 115;
UPDATE sys_menu SET menu_name = 'System Interface', remark = 'System Interface Menu' WHERE menu_id = 116;
-- 三级菜单
UPDATE sys_menu SET menu_name = 'Operation Log', remark = 'Operation Log Menu' WHERE menu_id = 500;
UPDATE sys_menu SET menu_name = 'Login Log', remark = 'Login Log Menu' WHERE menu_id = 501;

-- 更新字典类型表中的字典名称和备注
UPDATE sys_dict_type SET dict_name = 'User Gender', remark = 'User Gender List' WHERE dict_id = 1;
UPDATE sys_dict_type SET dict_name = 'Menu Status', remark = 'Menu Status List' WHERE dict_id = 2;
UPDATE sys_dict_type SET dict_name = 'System Switch', remark = 'System Switch List' WHERE dict_id = 3;
UPDATE sys_dict_type SET dict_name = 'Task Status', remark = 'Task Status List' WHERE dict_id = 4;
UPDATE sys_dict_type SET dict_name = 'Task Group', remark = 'Task Group List' WHERE dict_id = 5;
UPDATE sys_dict_type SET dict_name = 'System Yes/No', remark = 'System Yes/No List' WHERE dict_id = 6;
UPDATE sys_dict_type SET dict_name = 'Notice Type', remark = 'Notice Type List' WHERE dict_id = 7;
UPDATE sys_dict_type SET dict_name = 'Notice Status', remark = 'Notice Status List' WHERE dict_id = 8;
UPDATE sys_dict_type SET dict_name = 'Operation Type', remark = 'Operation Type List' WHERE dict_id = 9;
UPDATE sys_dict_type SET dict_name = 'System Status', remark = 'Login Status List' WHERE dict_id = 10;

-- 更新字典数据表中的字典标签和备注
-- 用户性别
UPDATE sys_dict_data SET dict_label = 'Male', remark = 'Gender Male' WHERE dict_code = 1;
UPDATE sys_dict_data SET dict_label = 'Female', remark = 'Gender Female' WHERE dict_code = 2;
UPDATE sys_dict_data SET dict_label = 'Unknown', remark = 'Gender Unknown' WHERE dict_code = 3;
-- 菜单状态
UPDATE sys_dict_data SET dict_label = 'Show', remark = 'Show Menu' WHERE dict_code = 4;
UPDATE sys_dict_data SET dict_label = 'Hide', remark = 'Hide Menu' WHERE dict_code = 5;
-- 系统开关
UPDATE sys_dict_data SET dict_label = 'Normal', remark = 'Normal Status' WHERE dict_code = 6;
UPDATE sys_dict_data SET dict_label = 'Disabled', remark = 'Disabled Status' WHERE dict_code = 7;
-- 任务状态
UPDATE sys_dict_data SET dict_label = 'Normal', remark = 'Normal Status' WHERE dict_code = 8;
UPDATE sys_dict_data SET dict_label = 'Paused', remark = 'Disabled Status' WHERE dict_code = 9;
-- 任务分组
UPDATE sys_dict_data SET dict_label = 'Default', remark = 'Default Group' WHERE dict_code = 10;
UPDATE sys_dict_data SET dict_label = 'System', remark = 'System Group' WHERE dict_code = 11;
-- 系统是否
UPDATE sys_dict_data SET dict_label = 'Yes', remark = 'System Default Yes' WHERE dict_code = 12;
UPDATE sys_dict_data SET dict_label = 'No', remark = 'System Default No' WHERE dict_code = 13;
-- 通知类型
UPDATE sys_dict_data SET dict_label = 'Notice', remark = 'Notice' WHERE dict_code = 14;
UPDATE sys_dict_data SET dict_label = 'Announcement', remark = 'Announcement' WHERE dict_code = 15;
-- 通知状态
UPDATE sys_dict_data SET dict_label = 'Normal', remark = 'Normal Status' WHERE dict_code = 16;
UPDATE sys_dict_data SET dict_label = 'Closed', remark = 'Closed Status' WHERE dict_code = 17;
-- 操作类型
UPDATE sys_dict_data SET dict_label = 'Other', remark = 'Other Operation' WHERE dict_code = 18;
UPDATE sys_dict_data SET dict_label = 'Add', remark = 'Add Operation' WHERE dict_code = 19;
