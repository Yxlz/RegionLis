-- ----------------------------
-- 1、机构（部门）表
-- ----------------------------
create sequence seq_rl_sys_org
 increment by 1
 start with 200
 nomaxvalue
 nominvalue
 cache 20;

create table rl_sys_org (
  org_id            number(20)      not null,
  parent_id         number(20)      default 0,
  ancestors         varchar2(50)    default '',
  org_name          varchar2(30)    default '',
  order_num         number(4)       default 0,
  discount          number(3,2)     default 1.00,
  leader            varchar2(20)    default null,
  phone             varchar2(11)    default null,
  email             varchar2(50)    default null,
  status            char(1)         default '0',
  del_flag          char(1)         default '0',
  create_by         varchar2(64)    default '',
  create_time 	    date,
  update_by         varchar2(64)    default '',
  update_time       date
);

alter table rl_sys_org add constraint pk_rl_sys_org primary key (org_id);

comment on table  rl_sys_org              is '机构信息表';
comment on column rl_sys_org.org_id       is '机构主键seq_rl_sys_org.nextval';
comment on column rl_sys_org.parent_id    is '父部门id';
comment on column rl_sys_org.ancestors    is '祖级列表';
comment on column rl_sys_org.org_name     is '机构名称';
comment on column rl_sys_org.order_num    is '显示顺序';
comment on column rl_sys_org.discount     is '项目折扣';
comment on column rl_sys_org.leader       is '负责人';
comment on column rl_sys_org.phone        is '联系电话';
comment on column rl_sys_org.email        is '邮箱';
comment on column rl_sys_org.status       is '机构状态（0正常 1停用）';
comment on column rl_sys_org.del_flag     is '删除标志（0代表存在 2代表删除）';
comment on column rl_sys_org.create_by    is '创建者';
comment on column rl_sys_org.create_time  is '创建时间';
comment on column rl_sys_org.update_by    is '更新者';
comment on column rl_sys_org.update_time  is '更新时间';

-- ----------------------------
-- 初始化-部门表数据
-- ----------------------------
insert into rl_sys_org values(100,  0,   '0',          '四川大学华西第二医院',   0, 1.00,  '负责人', '15888888888', 'ry@qq.com', '0', '0', 'admin', TO_DATE('2018-03-16 11-33-00', 'YYYY-MM-DD HH24:MI:SS'), 'ry', TO_DATE('2018-03-16 11-33-00', 'YYYY-MM-DD HH24:MI:SS'));
insert into rl_sys_org values(101,  100, '0,100',      '成华妇幼', 1, 0.50,  '院长', '15888888888', 'ry@qq.com', '0', '0', 'admin', TO_DATE('2018-03-16 11-33-00', 'YYYY-MM-DD HH24:MI:SS'), 'ry', TO_DATE('2018-03-16 11-33-00', 'YYYY-MM-DD HH24:MI:SS'));
insert into rl_sys_org values(102,  100, '0,100',      '新都区人民医院', 2, 0.85,  'XXX', '15888888888', 'ry@qq.com', '0', '0', 'admin', TO_DATE('2018-03-16 11-33-00', 'YYYY-MM-DD HH24:MI:SS'), 'ry', TO_DATE('2018-03-16 11-33-00', 'YYYY-MM-DD HH24:MI:SS'));


-- ----------------------------
-- 2、用户信息表
-- ----------------------------
create sequence seq_rl_sys_user
 increment by 1
 start with 100
 nomaxvalue
 nominvalue
 cache 20;

 create table rl_sys_user (
  user_id           number(20)      not null,
  org_id            number(20)      default null,
  login_name        varchar2(30)    not null,
  user_name         varchar2(30)    default '',
  user_type         varchar2(2)     default '00',
  email             varchar2(50)    default '',
  phonenumber       varchar2(11)    default '',
  sex               char(1)         default '0',
  avatar            varchar2(100)   default '',
  password          varchar2(100)   default '',
  salt              varchar2(20)    default '',
  status            char(1)         default '0',
  del_flag          char(1)         default '0',
  login_ip          varchar2(50)    default '',
  login_date        date,
  create_by         varchar2(64),
  create_time 	    date,
  update_by         varchar2(64)    default '',
  update_time       date,
  remark            varchar2(500)   default ''
);

alter table rl_sys_user add constraint pk_rl_sys_user primary key (user_id);

comment on table  rl_sys_user              is '用户信息表';
comment on column rl_sys_user.user_id      is '用户主键seq_rl_sys_user.nextval';
comment on column rl_sys_user.org_id       is '机构ID';
comment on column rl_sys_user.login_name   is '登陆账号';
comment on column rl_sys_user.user_name    is '用户昵称';
comment on column rl_sys_user.user_type    is '用户类型（00系统用户 01注册用户）';
comment on column rl_sys_user.email        is '用户邮箱';
comment on column rl_sys_user.phonenumber  is '手机号码';
comment on column rl_sys_user.sex          is '用户性别（0男 1女 2未知）';
comment on column rl_sys_user.avatar       is '头像路径';
comment on column rl_sys_user.password     is '密码';
comment on column rl_sys_user.salt         is '盐加密';
comment on column rl_sys_user.status       is '帐号状态（0正常 1停用）';
comment on column rl_sys_user.del_flag     is '删除标志（0代表存在 2代表删除）';
comment on column rl_sys_user.login_ip     is '最后登陆IP';
comment on column rl_sys_user.login_date   is '最后登陆时间';
comment on column rl_sys_user.create_by    is '创建者';
comment on column rl_sys_user.create_time  is '创建时间';
comment on column rl_sys_user.update_by    is '更新者';
comment on column rl_sys_user.update_time  is '更新时间';
comment on column rl_sys_user.remark       is '备注';

-- ----------------------------
-- 初始化-用户信息表数据
-- ----------------------------
insert into rl_sys_user values(1,  100, 'admin', '雨行', '00', 'ry@163.com', '15888888888', '1', '', '29c67a30398638269fe600f73a054934', '111111', '0', '0', '127.0.0.1', TO_DATE('2018-03-16 11-33-00', 'YYYY-MM-DD HH24:MI:SS'), 'admin', TO_DATE('2018-03-16 11-33-00', 'YYYY-MM-DD HH24:MI:SS'), 'rl', TO_DATE('2018-03-16 11-33-00', 'YYYY-MM-DD HH24:MI:SS'), '管理员');
insert into rl_sys_user values(2,  102, 'rl',    '若依', '00', 'ry@qq.com',  '15666666666', '1', '', '8e6d98b90472783cc73c17047ddccf36', '222222', '0', '0', '127.0.0.1', TO_DATE('2018-03-16 11-33-00', 'YYYY-MM-DD HH24:MI:SS'), 'admin', TO_DATE('2018-03-16 11-33-00', 'YYYY-MM-DD HH24:MI:SS'), 'rl', TO_DATE('2018-03-16 11-33-00', 'YYYY-MM-DD HH24:MI:SS'), '测试员');


-- ----------------------------
-- 3、岗位信息表
-- ----------------------------
create sequence seq_rl_sys_post
 increment by 1
 start with 10
 nomaxvalue
 nominvalue
 cache 20;

create table rl_sys_post
(
  post_id           number(20)      not null,
  post_code         varchar2(64)    not null,
  post_name         varchar2(50)    not null,
  post_sort         number(4)       not null,
  status            char(1)         not null,
  create_by         varchar2(64)    default '',
  create_time       date,
  update_by         varchar2(64)    default '',
  update_time       date,
  remark            varchar2(500)
);

alter table rl_sys_post add constraint pk_rl_sys_post primary key (post_id);

comment on table  rl_sys_post              is '岗位信息表';
comment on column rl_sys_post.post_id      is '岗位主键seq_rl_sys_post.nextval';
comment on column rl_sys_post.post_code    is '岗位编码';
comment on column rl_sys_post.post_name    is '岗位名称';
comment on column rl_sys_post.post_sort    is '显示顺序';
comment on column rl_sys_post.status       is '状态（0正常 1停用）';
comment on column rl_sys_post.create_by    is '创建者';
comment on column rl_sys_post.create_time  is '创建时间';
comment on column rl_sys_post.update_by    is '更新者';
comment on column rl_sys_post.update_time  is '更新时间';
comment on column rl_sys_post.remark       is '备注';

-- ----------------------------
-- 初始化-岗位信息表数据
-- ----------------------------
insert into rl_sys_post values(1, 'ceo',  '董事长',    1, '0', 'admin', TO_DATE('2018-03-16 11-33-00', 'YYYY-MM-DD HH24:MI:SS'), 'ry', TO_DATE('2018-03-16 11-33-00', 'YYYY-MM-DD HH24:MI:SS'), '');
insert into rl_sys_post values(2, 'se',   '项目经理',  2, '0', 'admin', TO_DATE('2018-03-16 11-33-00', 'YYYY-MM-DD HH24:MI:SS'), 'ry', TO_DATE('2018-03-16 11-33-00', 'YYYY-MM-DD HH24:MI:SS'), '');
insert into rl_sys_post values(3, 'hr',   '人力资源',  3, '0', 'admin', TO_DATE('2018-03-16 11-33-00', 'YYYY-MM-DD HH24:MI:SS'), 'ry', TO_DATE('2018-03-16 11-33-00', 'YYYY-MM-DD HH24:MI:SS'), '');
insert into rl_sys_post values(4, 'user', '普通员工',  4, '0', 'admin', TO_DATE('2018-03-16 11-33-00', 'YYYY-MM-DD HH24:MI:SS'), 'ry', TO_DATE('2018-03-16 11-33-00', 'YYYY-MM-DD HH24:MI:SS'), '');

-- ----------------------------
-- 4、角色信息表
-- ----------------------------
create sequence seq_rl_sys_role
 increment by 1
 start with 100
 nomaxvalue
 nominvalue
 cache 20;

create table rl_sys_role (
  role_id           number(20)      not null,
  role_name         varchar2(30)    not null,
  role_key          varchar2(100)   not null,
  role_sort         number(4)       not null,
  data_scope        char(1)         default '1',
  status            char(1)         not null,
  del_flag          char(1)         default '0',
  create_by         varchar2(64)    default '',
  create_time       date,
  update_by         varchar2(64)    default '',
  update_time       date,
  remark            varchar2(500)   default null
);

alter table rl_sys_role add constraint pk_rl_sys_role primary key (role_id);

comment on table  rl_sys_role              is '角色信息表';
comment on column rl_sys_role.role_id      is '角色主键seq_rl_sys_role.nextval';
comment on column rl_sys_role.role_name    is '角色名称';
comment on column rl_sys_role.role_key     is '角色权限字符串';
comment on column rl_sys_role.role_sort    is '显示顺序';
comment on column rl_sys_role.data_scope   is '数据范围（1：全部数据权限 2：自定数据权限 3：本部门数据权限 4：本部门及以下数据权限）';
comment on column rl_sys_role.status       is '角色状态（0正常 1停用）';
comment on column rl_sys_role.del_flag     is '删除标志（0代表存在 2代表删除）';
comment on column rl_sys_role.create_by    is '创建者';
comment on column rl_sys_role.create_time  is '创建时间';
comment on column rl_sys_role.update_by    is '更新者';
comment on column rl_sys_role.update_time  is '更新时间';
comment on column rl_sys_role.remark       is '备注';

-- ----------------------------
-- 初始化-角色信息表数据
-- ----------------------------
insert into rl_sys_role values('1', '管理员',   'admin',  1, 1, '0', '0', 'admin', TO_DATE('2018-03-16 11-33-00', 'YYYY-MM-DD HH24:MI:SS'), 'ry', TO_DATE('2018-03-16 11-33-00', 'YYYY-MM-DD HH24:MI:SS'), '管理员');
insert into rl_sys_role values('2', '普通角色', 'common', 2, 2, '0', '0', 'admin', TO_DATE('2018-03-16 11-33-00', 'YYYY-MM-DD HH24:MI:SS'), 'ry', TO_DATE('2018-03-16 11-33-00', 'YYYY-MM-DD HH24:MI:SS'), '普通角色');


-- ----------------------------
-- 5、菜单权限表
-- ----------------------------
create sequence seq_rl_sys_menu
 increment by 1
 start with 2000
 nomaxvalue
 nominvalue
 cache 20;

create table rl_sys_menu (
  menu_id           number(20)      not null,
  menu_name         varchar2(50)    not null,
  parent_id         number(20)      default 0,
  order_num         number(4)       default 0,
  url               varchar2(200)   default '#',
  target            varchar2(20)    default '',
  menu_type         char(1)         default '',
  visible           char(1)         default 0,
  perms             varchar2(100)   default null,
  icon              varchar2(100)   default '#',
  create_by         varchar2(64)    default '',
  create_time       date,
  update_by         varchar2(64)    default '',
  update_time       date ,
  remark            varchar2(500)   default ''
);

alter table rl_sys_menu add constraint pk_rl_sys_menu primary key (menu_id);

comment on table  rl_sys_menu              is '菜单权限表';
comment on column rl_sys_menu.menu_id      is '菜单主键seq_rl_sys_menu.nextval';
comment on column rl_sys_menu.menu_name    is '菜单名称';
comment on column rl_sys_menu.parent_id    is '父菜单ID';
comment on column rl_sys_menu.order_num    is '显示顺序';
comment on column rl_sys_menu.url          is '请求地址';
comment on column rl_sys_menu.target       is '打开方式（menuItem页签 menuBlank新窗口）';
comment on column rl_sys_menu.menu_type    is '菜单类型（M目录 C菜单 F按钮）';
comment on column rl_sys_menu.visible      is '菜单状态（0显示 1隐藏）';
comment on column rl_sys_menu.perms        is '权限标识';
comment on column rl_sys_menu.icon         is '菜单图标';
comment on column rl_sys_menu.create_by    is '创建者';
comment on column rl_sys_menu.create_time  is '创建时间';
comment on column rl_sys_menu.update_by    is '更新者';
comment on column rl_sys_menu.update_time  is '更新时间';
comment on column rl_sys_menu.remark       is '备注';

-- ----------------------------
-- 初始化-菜单信息表数据
-- ----------------------------
-- 一级菜单
insert into rl_sys_menu values('1', '系统管理', '0', '1', '#',                '',          'M', '0', '', 'fa fa-gear',           'admin', TO_DATE('2018-03-16 11-33-00', 'YYYY-MM-DD HH24:MI:SS'), 'ry', TO_DATE('2018-03-16 11-33-00', 'YYYY-MM-DD HH24:MI:SS'), '系统管理目录');
insert into rl_sys_menu values('2', '系统监控', '0', '2', '#',                '',          'M', '0', '', 'fa fa-video-camera',   'admin', TO_DATE('2018-03-16 11-33-00', 'YYYY-MM-DD HH24:MI:SS'), 'ry', TO_DATE('2018-03-16 11-33-00', 'YYYY-MM-DD HH24:MI:SS'), '系统监控目录');
insert into rl_sys_menu values('3', '系统工具', '0', '3', '#',                '',          'M', '0', '', 'fa fa-bars',           'admin', TO_DATE('2018-03-16 11-33-00', 'YYYY-MM-DD HH24:MI:SS'), 'ry', TO_DATE('2018-03-16 11-33-00', 'YYYY-MM-DD HH24:MI:SS'), '系统工具目录');
insert into rl_sys_menu values('4', '若依官网', '0', '4', 'http://ruoyi.vip', 'menuBlank', 'C', '0', '', 'fa fa-location-arrow', 'admin', TO_DATE('2018-03-16 11-33-00', 'YYYY-MM-DD HH24:MI:SS'), 'ry', TO_DATE('2018-03-16 11-33-00', 'YYYY-MM-DD HH24:MI:SS'), '若依官网地址');
-- 二级菜单
insert into rl_sys_menu values('100',  '用户管理', '1', '1', '/system/user',          '', 'C', '0', 'system:user:view',         '#', 'admin', TO_DATE('2018-03-16 11-33-00', 'YYYY-MM-DD HH24:MI:SS'), 'ry', TO_DATE('2018-03-16 11-33-00', 'YYYY-MM-DD HH24:MI:SS'), '用户管理菜单');
insert into rl_sys_menu values('101',  '角色管理', '1', '2', '/system/role',          '', 'C', '0', 'system:role:view',         '#', 'admin', TO_DATE('2018-03-16 11-33-00', 'YYYY-MM-DD HH24:MI:SS'), 'ry', TO_DATE('2018-03-16 11-33-00', 'YYYY-MM-DD HH24:MI:SS'), '角色管理菜单');
insert into rl_sys_menu values('102',  '菜单管理', '1', '3', '/system/menu',          '', 'C', '0', 'system:menu:view',         '#', 'admin', TO_DATE('2018-03-16 11-33-00', 'YYYY-MM-DD HH24:MI:SS'), 'ry', TO_DATE('2018-03-16 11-33-00', 'YYYY-MM-DD HH24:MI:SS'), '菜单管理菜单');
insert into rl_sys_menu values('103',  '部门管理', '1', '4', '/system/dept',          '', 'C', '0', 'system:dept:view',         '#', 'admin', TO_DATE('2018-03-16 11-33-00', 'YYYY-MM-DD HH24:MI:SS'), 'ry', TO_DATE('2018-03-16 11-33-00', 'YYYY-MM-DD HH24:MI:SS'), '部门管理菜单');
insert into rl_sys_menu values('104',  '岗位管理', '1', '5', '/system/post',          '', 'C', '0', 'system:post:view',         '#', 'admin', TO_DATE('2018-03-16 11-33-00', 'YYYY-MM-DD HH24:MI:SS'), 'ry', TO_DATE('2018-03-16 11-33-00', 'YYYY-MM-DD HH24:MI:SS'), '岗位管理菜单');
insert into rl_sys_menu values('105',  '字典管理', '1', '6', '/system/dict',          '', 'C', '0', 'system:dict:view',         '#', 'admin', TO_DATE('2018-03-16 11-33-00', 'YYYY-MM-DD HH24:MI:SS'), 'ry', TO_DATE('2018-03-16 11-33-00', 'YYYY-MM-DD HH24:MI:SS'), '字典管理菜单');
insert into rl_sys_menu values('106',  '参数设置', '1', '7', '/system/config',        '', 'C', '0', 'system:config:view',       '#', 'admin', TO_DATE('2018-03-16 11-33-00', 'YYYY-MM-DD HH24:MI:SS'), 'ry', TO_DATE('2018-03-16 11-33-00', 'YYYY-MM-DD HH24:MI:SS'), '参数设置菜单');
insert into rl_sys_menu values('107',  '通知公告', '1', '8', '/system/notice',        '', 'C', '0', 'system:notice:view',       '#', 'admin', TO_DATE('2018-03-16 11-33-00', 'YYYY-MM-DD HH24:MI:SS'), 'ry', TO_DATE('2018-03-16 11-33-00', 'YYYY-MM-DD HH24:MI:SS'), '通知公告菜单');
insert into rl_sys_menu values('108',  '日志管理', '1', '9', '#',                     '', 'M', '0', '',                         '#', 'admin', TO_DATE('2018-03-16 11-33-00', 'YYYY-MM-DD HH24:MI:SS'), 'ry', TO_DATE('2018-03-16 11-33-00', 'YYYY-MM-DD HH24:MI:SS'), '日志管理菜单');
insert into rl_sys_menu values('109',  '在线用户', '2', '1', '/monitor/online',       '', 'C', '0', 'monitor:online:view',      '#', 'admin', TO_DATE('2018-03-16 11-33-00', 'YYYY-MM-DD HH24:MI:SS'), 'ry', TO_DATE('2018-03-16 11-33-00', 'YYYY-MM-DD HH24:MI:SS'), '在线用户菜单');
insert into rl_sys_menu values('110',  '定时任务', '2', '2', '/monitor/job',          '', 'C', '0', 'monitor:job:view',         '#', 'admin', TO_DATE('2018-03-16 11-33-00', 'YYYY-MM-DD HH24:MI:SS'), 'ry', TO_DATE('2018-03-16 11-33-00', 'YYYY-MM-DD HH24:MI:SS'), '定时任务菜单');
insert into rl_sys_menu values('111',  '数据监控', '2', '3', '/monitor/data',         '', 'C', '0', 'monitor:data:view',        '#', 'admin', TO_DATE('2018-03-16 11-33-00', 'YYYY-MM-DD HH24:MI:SS'), 'ry', TO_DATE('2018-03-16 11-33-00', 'YYYY-MM-DD HH24:MI:SS'), '数据监控菜单');
insert into rl_sys_menu values('112',  '服务监控', '2', '3', '/monitor/server',       '', 'C', '0', 'monitor:server:view',      '#', 'admin', TO_DATE('2018-03-16 11-33-00', 'YYYY-MM-DD HH24:MI:SS'), 'ry', TO_DATE('2018-03-16 11-33-00', 'YYYY-MM-DD HH24:MI:SS'), '服务监控菜单');
insert into rl_sys_menu values('113',  '表单构建', '3', '1', '/tool/build',           '', 'C', '0', 'tool:build:view',          '#', 'admin', TO_DATE('2018-03-16 11-33-00', 'YYYY-MM-DD HH24:MI:SS'), 'ry', TO_DATE('2018-03-16 11-33-00', 'YYYY-MM-DD HH24:MI:SS'), '表单构建菜单');
insert into rl_sys_menu values('114',  '代码生成', '3', '2', '/tool/gen',             '', 'C', '0', 'tool:gen:view',            '#', 'admin', TO_DATE('2018-03-16 11-33-00', 'YYYY-MM-DD HH24:MI:SS'), 'ry', TO_DATE('2018-03-16 11-33-00', 'YYYY-MM-DD HH24:MI:SS'), '代码生成菜单');
insert into rl_sys_menu values('115',  '系统接口', '3', '3', '/tool/swagger',         '', 'C', '0', 'tool:swagger:view',        '#', 'admin', TO_DATE('2018-03-16 11-33-00', 'YYYY-MM-DD HH24:MI:SS'), 'ry', TO_DATE('2018-03-16 11-33-00', 'YYYY-MM-DD HH24:MI:SS'), '系统接口菜单');
-- 三级菜单
insert into rl_sys_menu values('500',  '操作日志', '108', '1', '/monitor/operlog',    '', 'C', '0', 'monitor:operlog:view',     '#', 'admin', TO_DATE('2018-03-16 11-33-00', 'YYYY-MM-DD HH24:MI:SS'), 'ry', TO_DATE('2018-03-16 11-33-00', 'YYYY-MM-DD HH24:MI:SS'), '操作日志菜单');
insert into rl_sys_menu values('501',  '登录日志', '108', '2', '/monitor/logininfor', '', 'C', '0', 'monitor:logininfor:view',  '#', 'admin', TO_DATE('2018-03-16 11-33-00', 'YYYY-MM-DD HH24:MI:SS'), 'ry', TO_DATE('2018-03-16 11-33-00', 'YYYY-MM-DD HH24:MI:SS'), '登录日志菜单');
-- 用户管理按钮
insert into rl_sys_menu values('1000', '用户查询', '100', '1',  '#', '',  'F', '0', 'system:user:list',        '#', 'admin', TO_DATE('2018-03-16 11-33-00', 'YYYY-MM-DD HH24:MI:SS'), 'ry', TO_DATE('2018-03-16 11-33-00', 'YYYY-MM-DD HH24:MI:SS'), '');
insert into rl_sys_menu values('1001', '用户新增', '100', '2',  '#', '',  'F', '0', 'system:user:add',         '#', 'admin', TO_DATE('2018-03-16 11-33-00', 'YYYY-MM-DD HH24:MI:SS'), 'ry', TO_DATE('2018-03-16 11-33-00', 'YYYY-MM-DD HH24:MI:SS'), '');
insert into rl_sys_menu values('1002', '用户修改', '100', '3',  '#', '',  'F', '0', 'system:user:edit',        '#', 'admin', TO_DATE('2018-03-16 11-33-00', 'YYYY-MM-DD HH24:MI:SS'), 'ry', TO_DATE('2018-03-16 11-33-00', 'YYYY-MM-DD HH24:MI:SS'), '');
insert into rl_sys_menu values('1003', '用户删除', '100', '4',  '#', '',  'F', '0', 'system:user:remove',      '#', 'admin', TO_DATE('2018-03-16 11-33-00', 'YYYY-MM-DD HH24:MI:SS'), 'ry', TO_DATE('2018-03-16 11-33-00', 'YYYY-MM-DD HH24:MI:SS'), '');
insert into rl_sys_menu values('1004', '用户导出', '100', '5',  '#', '',  'F', '0', 'system:user:export',      '#', 'admin', TO_DATE('2018-03-16 11-33-00', 'YYYY-MM-DD HH24:MI:SS'), 'ry', TO_DATE('2018-03-16 11-33-00', 'YYYY-MM-DD HH24:MI:SS'), '');
insert into rl_sys_menu values('1005', '用户导入', '100', '6',  '#', '',  'F', '0', 'system:user:import',      '#', 'admin', TO_DATE('2018-03-16 11-33-00', 'YYYY-MM-DD HH24:MI:SS'), 'ry', TO_DATE('2018-03-16 11-33-00', 'YYYY-MM-DD HH24:MI:SS'), '');
insert into rl_sys_menu values('1006', '重置密码', '100', '7',  '#', '',  'F', '0', 'system:user:resetPwd',    '#', 'admin', TO_DATE('2018-03-16 11-33-00', 'YYYY-MM-DD HH24:MI:SS'), 'ry', TO_DATE('2018-03-16 11-33-00', 'YYYY-MM-DD HH24:MI:SS'), '');
-- 角色管理按钮
insert into rl_sys_menu values('1007', '角色查询', '101', '1',  '#', '',  'F', '0', 'system:role:list',        '#', 'admin', TO_DATE('2018-03-16 11-33-00', 'YYYY-MM-DD HH24:MI:SS'), 'ry', TO_DATE('2018-03-16 11-33-00', 'YYYY-MM-DD HH24:MI:SS'), '');
insert into rl_sys_menu values('1008', '角色新增', '101', '2',  '#', '',  'F', '0', 'system:role:add',         '#', 'admin', TO_DATE('2018-03-16 11-33-00', 'YYYY-MM-DD HH24:MI:SS'), 'ry', TO_DATE('2018-03-16 11-33-00', 'YYYY-MM-DD HH24:MI:SS'), '');
insert into rl_sys_menu values('1009', '角色修改', '101', '3',  '#', '',  'F', '0', 'system:role:edit',        '#', 'admin', TO_DATE('2018-03-16 11-33-00', 'YYYY-MM-DD HH24:MI:SS'), 'ry', TO_DATE('2018-03-16 11-33-00', 'YYYY-MM-DD HH24:MI:SS'), '');
insert into rl_sys_menu values('1010', '角色删除', '101', '4',  '#', '',  'F', '0', 'system:role:remove',      '#', 'admin', TO_DATE('2018-03-16 11-33-00', 'YYYY-MM-DD HH24:MI:SS'), 'ry', TO_DATE('2018-03-16 11-33-00', 'YYYY-MM-DD HH24:MI:SS'), '');
insert into rl_sys_menu values('1011', '角色导出', '101', '5',  '#', '',  'F', '0', 'system:role:export',      '#', 'admin', TO_DATE('2018-03-16 11-33-00', 'YYYY-MM-DD HH24:MI:SS'), 'ry', TO_DATE('2018-03-16 11-33-00', 'YYYY-MM-DD HH24:MI:SS'), '');
-- 菜单管理按钮
insert into rl_sys_menu values('1012', '菜单查询', '102', '1',  '#', '',  'F', '0', 'system:menu:list',        '#', 'admin', TO_DATE('2018-03-16 11-33-00', 'YYYY-MM-DD HH24:MI:SS'), 'ry', TO_DATE('2018-03-16 11-33-00', 'YYYY-MM-DD HH24:MI:SS'), '');
insert into rl_sys_menu values('1013', '菜单新增', '102', '2',  '#', '',  'F', '0', 'system:menu:add',         '#', 'admin', TO_DATE('2018-03-16 11-33-00', 'YYYY-MM-DD HH24:MI:SS'), 'ry', TO_DATE('2018-03-16 11-33-00', 'YYYY-MM-DD HH24:MI:SS'), '');
insert into rl_sys_menu values('1014', '菜单修改', '102', '3',  '#', '',  'F', '0', 'system:menu:edit',        '#', 'admin', TO_DATE('2018-03-16 11-33-00', 'YYYY-MM-DD HH24:MI:SS'), 'ry', TO_DATE('2018-03-16 11-33-00', 'YYYY-MM-DD HH24:MI:SS'), '');
insert into rl_sys_menu values('1015', '菜单删除', '102', '4',  '#', '',  'F', '0', 'system:menu:remove',      '#', 'admin', TO_DATE('2018-03-16 11-33-00', 'YYYY-MM-DD HH24:MI:SS'), 'ry', TO_DATE('2018-03-16 11-33-00', 'YYYY-MM-DD HH24:MI:SS'), '');
-- 部门管理按钮
insert into rl_sys_menu values('1016', '部门查询', '103', '1',  '#', '',  'F', '0', 'system:dept:list',        '#', 'admin', TO_DATE('2018-03-16 11-33-00', 'YYYY-MM-DD HH24:MI:SS'), 'ry', TO_DATE('2018-03-16 11-33-00', 'YYYY-MM-DD HH24:MI:SS'), '');
insert into rl_sys_menu values('1017', '部门新增', '103', '2',  '#', '',  'F', '0', 'system:dept:add',         '#', 'admin', TO_DATE('2018-03-16 11-33-00', 'YYYY-MM-DD HH24:MI:SS'), 'ry', TO_DATE('2018-03-16 11-33-00', 'YYYY-MM-DD HH24:MI:SS'), '');
insert into rl_sys_menu values('1018', '部门修改', '103', '3',  '#', '',  'F', '0', 'system:dept:edit',        '#', 'admin', TO_DATE('2018-03-16 11-33-00', 'YYYY-MM-DD HH24:MI:SS'), 'ry', TO_DATE('2018-03-16 11-33-00', 'YYYY-MM-DD HH24:MI:SS'), '');
insert into rl_sys_menu values('1019', '部门删除', '103', '4',  '#', '',  'F', '0', 'system:dept:remove',      '#', 'admin', TO_DATE('2018-03-16 11-33-00', 'YYYY-MM-DD HH24:MI:SS'), 'ry', TO_DATE('2018-03-16 11-33-00', 'YYYY-MM-DD HH24:MI:SS'), '');
-- 岗位管理按钮
insert into rl_sys_menu values('1020', '岗位查询', '104', '1',  '#', '',  'F', '0', 'system:post:list',        '#', 'admin', TO_DATE('2018-03-16 11-33-00', 'YYYY-MM-DD HH24:MI:SS'), 'ry', TO_DATE('2018-03-16 11-33-00', 'YYYY-MM-DD HH24:MI:SS'), '');
insert into rl_sys_menu values('1021', '岗位新增', '104', '2',  '#', '',  'F', '0', 'system:post:add',         '#', 'admin', TO_DATE('2018-03-16 11-33-00', 'YYYY-MM-DD HH24:MI:SS'), 'ry', TO_DATE('2018-03-16 11-33-00', 'YYYY-MM-DD HH24:MI:SS'), '');
insert into rl_sys_menu values('1022', '岗位修改', '104', '3',  '#', '',  'F', '0', 'system:post:edit',        '#', 'admin', TO_DATE('2018-03-16 11-33-00', 'YYYY-MM-DD HH24:MI:SS'), 'ry', TO_DATE('2018-03-16 11-33-00', 'YYYY-MM-DD HH24:MI:SS'), '');
insert into rl_sys_menu values('1023', '岗位删除', '104', '4',  '#', '',  'F', '0', 'system:post:remove',      '#', 'admin', TO_DATE('2018-03-16 11-33-00', 'YYYY-MM-DD HH24:MI:SS'), 'ry', TO_DATE('2018-03-16 11-33-00', 'YYYY-MM-DD HH24:MI:SS'), '');
insert into rl_sys_menu values('1024', '岗位导出', '104', '5',  '#', '',  'F', '0', 'system:post:export',      '#', 'admin', TO_DATE('2018-03-16 11-33-00', 'YYYY-MM-DD HH24:MI:SS'), 'ry', TO_DATE('2018-03-16 11-33-00', 'YYYY-MM-DD HH24:MI:SS'), '');
-- 字典管理按钮
insert into rl_sys_menu values('1025', '字典查询', '105', '1', '#', '',  'F', '0', 'system:dict:list',         '#', 'admin', TO_DATE('2018-03-16 11-33-00', 'YYYY-MM-DD HH24:MI:SS'), 'ry', TO_DATE('2018-03-16 11-33-00', 'YYYY-MM-DD HH24:MI:SS'), '');
insert into rl_sys_menu values('1026', '字典新增', '105', '2', '#', '',  'F', '0', 'system:dict:add',          '#', 'admin', TO_DATE('2018-03-16 11-33-00', 'YYYY-MM-DD HH24:MI:SS'), 'ry', TO_DATE('2018-03-16 11-33-00', 'YYYY-MM-DD HH24:MI:SS'), '');
insert into rl_sys_menu values('1027', '字典修改', '105', '3', '#', '',  'F', '0', 'system:dict:edit',         '#', 'admin', TO_DATE('2018-03-16 11-33-00', 'YYYY-MM-DD HH24:MI:SS'), 'ry', TO_DATE('2018-03-16 11-33-00', 'YYYY-MM-DD HH24:MI:SS'), '');
insert into rl_sys_menu values('1028', '字典删除', '105', '4', '#', '',  'F', '0', 'system:dict:remove',       '#', 'admin', TO_DATE('2018-03-16 11-33-00', 'YYYY-MM-DD HH24:MI:SS'), 'ry', TO_DATE('2018-03-16 11-33-00', 'YYYY-MM-DD HH24:MI:SS'), '');
insert into rl_sys_menu values('1029', '字典导出', '105', '5', '#', '',  'F', '0', 'system:dict:export',       '#', 'admin', TO_DATE('2018-03-16 11-33-00', 'YYYY-MM-DD HH24:MI:SS'), 'ry', TO_DATE('2018-03-16 11-33-00', 'YYYY-MM-DD HH24:MI:SS'), '');
-- 参数设置按钮
insert into rl_sys_menu values('1030', '参数查询', '106', '1', '#', '',  'F', '0', 'system:config:list',      '#', 'admin', TO_DATE('2018-03-16 11-33-00', 'YYYY-MM-DD HH24:MI:SS'), 'ry', TO_DATE('2018-03-16 11-33-00', 'YYYY-MM-DD HH24:MI:SS'), '');
insert into rl_sys_menu values('1031', '参数新增', '106', '2', '#', '',  'F', '0', 'system:config:add',       '#', 'admin', TO_DATE('2018-03-16 11-33-00', 'YYYY-MM-DD HH24:MI:SS'), 'ry', TO_DATE('2018-03-16 11-33-00', 'YYYY-MM-DD HH24:MI:SS'), '');
insert into rl_sys_menu values('1032', '参数修改', '106', '3', '#', '',  'F', '0', 'system:config:edit',      '#', 'admin', TO_DATE('2018-03-16 11-33-00', 'YYYY-MM-DD HH24:MI:SS'), 'ry', TO_DATE('2018-03-16 11-33-00', 'YYYY-MM-DD HH24:MI:SS'), '');
insert into rl_sys_menu values('1033', '参数删除', '106', '4', '#', '',  'F', '0', 'system:config:remove',    '#', 'admin', TO_DATE('2018-03-16 11-33-00', 'YYYY-MM-DD HH24:MI:SS'), 'ry', TO_DATE('2018-03-16 11-33-00', 'YYYY-MM-DD HH24:MI:SS'), '');
insert into rl_sys_menu values('1034', '参数导出', '106', '5', '#', '',  'F', '0', 'system:config:export',    '#', 'admin', TO_DATE('2018-03-16 11-33-00', 'YYYY-MM-DD HH24:MI:SS'), 'ry', TO_DATE('2018-03-16 11-33-00', 'YYYY-MM-DD HH24:MI:SS'), '');
-- 通知公告按钮
insert into rl_sys_menu values('1035', '公告查询', '107', '1', '#', '',  'F', '0', 'system:notice:list',      '#', 'admin', TO_DATE('2018-03-16 11-33-00', 'YYYY-MM-DD HH24:MI:SS'), 'ry', TO_DATE('2018-03-16 11-33-00', 'YYYY-MM-DD HH24:MI:SS'), '');
insert into rl_sys_menu values('1036', '公告新增', '107', '2', '#', '',  'F', '0', 'system:notice:add',       '#', 'admin', TO_DATE('2018-03-16 11-33-00', 'YYYY-MM-DD HH24:MI:SS'), 'ry', TO_DATE('2018-03-16 11-33-00', 'YYYY-MM-DD HH24:MI:SS'), '');
insert into rl_sys_menu values('1037', '公告修改', '107', '3', '#', '',  'F', '0', 'system:notice:edit',      '#', 'admin', TO_DATE('2018-03-16 11-33-00', 'YYYY-MM-DD HH24:MI:SS'), 'ry', TO_DATE('2018-03-16 11-33-00', 'YYYY-MM-DD HH24:MI:SS'), '');
insert into rl_sys_menu values('1038', '公告删除', '107', '4', '#', '',  'F', '0', 'system:notice:remove',    '#', 'admin', TO_DATE('2018-03-16 11-33-00', 'YYYY-MM-DD HH24:MI:SS'), 'ry', TO_DATE('2018-03-16 11-33-00', 'YYYY-MM-DD HH24:MI:SS'), '');
-- 操作日志按钮
insert into rl_sys_menu values('1039', '操作查询', '500', '1', '#', '',  'F', '0', 'monitor:operlog:list',    '#', 'admin', TO_DATE('2018-03-16 11-33-00', 'YYYY-MM-DD HH24:MI:SS'), 'ry', TO_DATE('2018-03-16 11-33-00', 'YYYY-MM-DD HH24:MI:SS'), '');
insert into rl_sys_menu values('1040', '操作删除', '500', '2', '#', '',  'F', '0', 'monitor:operlog:remove',  '#', 'admin', TO_DATE('2018-03-16 11-33-00', 'YYYY-MM-DD HH24:MI:SS'), 'ry', TO_DATE('2018-03-16 11-33-00', 'YYYY-MM-DD HH24:MI:SS'), '');
insert into rl_sys_menu values('1041', '详细信息', '500', '3', '#', '',  'F', '0', 'monitor:operlog:detail',  '#', 'admin', TO_DATE('2018-03-16 11-33-00', 'YYYY-MM-DD HH24:MI:SS'), 'ry', TO_DATE('2018-03-16 11-33-00', 'YYYY-MM-DD HH24:MI:SS'), '');
insert into rl_sys_menu values('1042', '日志导出', '500', '4', '#', '',  'F', '0', 'monitor:operlog:export',  '#', 'admin', TO_DATE('2018-03-16 11-33-00', 'YYYY-MM-DD HH24:MI:SS'), 'ry', TO_DATE('2018-03-16 11-33-00', 'YYYY-MM-DD HH24:MI:SS'), '');
-- 登录日志按钮
insert into rl_sys_menu values('1043', '登录查询', '501', '1', '#', '',  'F', '0', 'monitor:logininfor:list',         '#', 'admin', TO_DATE('2018-03-16 11-33-00', 'YYYY-MM-DD HH24:MI:SS'), 'ry', TO_DATE('2018-03-16 11-33-00', 'YYYY-MM-DD HH24:MI:SS'), '');
insert into rl_sys_menu values('1044', '登录删除', '501', '2', '#', '',  'F', '0', 'monitor:logininfor:remove',       '#', 'admin', TO_DATE('2018-03-16 11-33-00', 'YYYY-MM-DD HH24:MI:SS'), 'ry', TO_DATE('2018-03-16 11-33-00', 'YYYY-MM-DD HH24:MI:SS'), '');
insert into rl_sys_menu values('1045', '日志导出', '501', '3', '#', '',  'F', '0', 'monitor:logininfor:export',       '#', 'admin', TO_DATE('2018-03-16 11-33-00', 'YYYY-MM-DD HH24:MI:SS'), 'ry', TO_DATE('2018-03-16 11-33-00', 'YYYY-MM-DD HH24:MI:SS'), '');
insert into rl_sys_menu values('1046', '账户解锁', '501', '4', '#', '',  'F', '0', 'monitor:logininfor:unlock',       '#', 'admin', TO_DATE('2018-03-16 11-33-00', 'YYYY-MM-DD HH24:MI:SS'), 'ry', TO_DATE('2018-03-16 11-33-00', 'YYYY-MM-DD HH24:MI:SS'), '');
-- 在线用户按钮
insert into rl_sys_menu values('1047', '在线查询', '109', '1', '#', '',  'F', '0', 'monitor:online:list',             '#', 'admin', TO_DATE('2018-03-16 11-33-00', 'YYYY-MM-DD HH24:MI:SS'), 'ry', TO_DATE('2018-03-16 11-33-00', 'YYYY-MM-DD HH24:MI:SS'), '');
insert into rl_sys_menu values('1048', '批量强退', '109', '2', '#', '',  'F', '0', 'monitor:online:batchForceLogout', '#', 'admin', TO_DATE('2018-03-16 11-33-00', 'YYYY-MM-DD HH24:MI:SS'), 'ry', TO_DATE('2018-03-16 11-33-00', 'YYYY-MM-DD HH24:MI:SS'), '');
insert into rl_sys_menu values('1049', '单条强退', '109', '3', '#', '',  'F', '0', 'monitor:online:forceLogout',      '#', 'admin', TO_DATE('2018-03-16 11-33-00', 'YYYY-MM-DD HH24:MI:SS'), 'ry', TO_DATE('2018-03-16 11-33-00', 'YYYY-MM-DD HH24:MI:SS'), '');
-- 定时任务按钮
insert into rl_sys_menu values('1050', '任务查询', '110', '1', '#', '',  'F', '0', 'monitor:job:list',                '#', 'admin', TO_DATE('2018-03-16 11-33-00', 'YYYY-MM-DD HH24:MI:SS'), 'ry', TO_DATE('2018-03-16 11-33-00', 'YYYY-MM-DD HH24:MI:SS'), '');
insert into rl_sys_menu values('1051', '任务新增', '110', '2', '#', '',  'F', '0', 'monitor:job:add',                 '#', 'admin', TO_DATE('2018-03-16 11-33-00', 'YYYY-MM-DD HH24:MI:SS'), 'ry', TO_DATE('2018-03-16 11-33-00', 'YYYY-MM-DD HH24:MI:SS'), '');
insert into rl_sys_menu values('1052', '任务修改', '110', '3', '#', '',  'F', '0', 'monitor:job:edit',                '#', 'admin', TO_DATE('2018-03-16 11-33-00', 'YYYY-MM-DD HH24:MI:SS'), 'ry', TO_DATE('2018-03-16 11-33-00', 'YYYY-MM-DD HH24:MI:SS'), '');
insert into rl_sys_menu values('1053', '任务删除', '110', '4', '#', '',  'F', '0', 'monitor:job:remove',              '#', 'admin', TO_DATE('2018-03-16 11-33-00', 'YYYY-MM-DD HH24:MI:SS'), 'ry', TO_DATE('2018-03-16 11-33-00', 'YYYY-MM-DD HH24:MI:SS'), '');
insert into rl_sys_menu values('1054', '状态修改', '110', '5', '#', '',  'F', '0', 'monitor:job:changeStatus',        '#', 'admin', TO_DATE('2018-03-16 11-33-00', 'YYYY-MM-DD HH24:MI:SS'), 'ry', TO_DATE('2018-03-16 11-33-00', 'YYYY-MM-DD HH24:MI:SS'), '');
insert into rl_sys_menu values('1055', '任务详细', '110', '6', '#', '',  'F', '0', 'monitor:job:detail',              '#', 'admin', TO_DATE('2018-03-16 11-33-00', 'YYYY-MM-DD HH24:MI:SS'), 'ry', TO_DATE('2018-03-16 11-33-00', 'YYYY-MM-DD HH24:MI:SS'), '');
insert into rl_sys_menu values('1056', '任务导出', '110', '7', '#', '',  'F', '0', 'monitor:job:export',              '#', 'admin', TO_DATE('2018-03-16 11-33-00', 'YYYY-MM-DD HH24:MI:SS'), 'ry', TO_DATE('2018-03-16 11-33-00', 'YYYY-MM-DD HH24:MI:SS'), '');
-- 代码生成按钮
insert into rl_sys_menu values('1057', '生成查询', '114', '1', '#', '',  'F', '0', 'tool:gen:list',     '#', 'admin', TO_DATE('2018-03-16 11-33-00', 'YYYY-MM-DD HH24:MI:SS'), 'ry', TO_DATE('2018-03-16 11-33-00', 'YYYY-MM-DD HH24:MI:SS'), '');
insert into rl_sys_menu values('1058', '生成修改', '114', '2', '#', '',  'F', '0', 'tool:gen:edit',     '#', 'admin', TO_DATE('2018-03-16 11-33-00', 'YYYY-MM-DD HH24:MI:SS'), 'ry', TO_DATE('2018-03-16 11-33-00', 'YYYY-MM-DD HH24:MI:SS'), '');
insert into rl_sys_menu values('1059', '生成删除', '114', '3', '#', '',  'F', '0', 'tool:gen:remove',   '#', 'admin', TO_DATE('2018-03-16 11-33-00', 'YYYY-MM-DD HH24:MI:SS'), 'ry', TO_DATE('2018-03-16 11-33-00', 'YYYY-MM-DD HH24:MI:SS'), '');
insert into rl_sys_menu values('1060', '预览代码', '114', '4', '#', '',  'F', '0', 'tool:gen:preview',  '#', 'admin', TO_DATE('2018-03-16 11-33-00', 'YYYY-MM-DD HH24:MI:SS'), 'ry', TO_DATE('2018-03-16 11-33-00', 'YYYY-MM-DD HH24:MI:SS'), '');
insert into rl_sys_menu values('1061', '生成代码', '114', '5', '#', '',  'F', '0', 'tool:gen:code',     '#', 'admin', TO_DATE('2018-03-16 11-33-00', 'YYYY-MM-DD HH24:MI:SS'), 'ry', TO_DATE('2018-03-16 11-33-00', 'YYYY-MM-DD HH24:MI:SS'), '');


-- ----------------------------
-- 6、用户和角色关联表  用户N-1角色
-- ----------------------------
create table rl_sys_user_role (
  user_id 	number(20)  not null,
  role_id 	number(20)  not null
);

alter table rl_sys_user_role add constraint pk_rl_sys_user_role primary key (user_id, role_id);

comment on table  rl_sys_user_role              is '用户和角色关联表';
comment on column rl_sys_user_role.user_id      is '用户ID';
comment on column rl_sys_user_role.role_id      is '角色ID';

-- ----------------------------
-- 初始化-用户和角色关联表数据
-- ----------------------------
insert into rl_sys_user_role values ('1', '1');
insert into rl_sys_user_role values ('2', '2');

-- ----------------------------
-- 7、角色和菜单关联表  角色1-N菜单
-- ----------------------------
create table rl_sys_role_menu (
  role_id 	number(20)  not null,
  menu_id 	number(20)  not null
);

alter table rl_sys_role_menu add constraint pk_rl_sys_role_menu primary key (role_id, menu_id);

comment on table  rl_sys_role_menu              is '角色和菜单关联表';
comment on column rl_sys_role_menu.role_id      is '角色ID';
comment on column rl_sys_role_menu.menu_id      is '菜单ID';

-- ----------------------------
-- 初始化-角色和菜单关联表数据
-- ----------------------------
insert into rl_sys_role_menu values ('2', '1');
insert into rl_sys_role_menu values ('2', '2');
insert into rl_sys_role_menu values ('2', '3');
insert into rl_sys_role_menu values ('2', '4');
insert into rl_sys_role_menu values ('2', '100');
insert into rl_sys_role_menu values ('2', '101');
insert into rl_sys_role_menu values ('2', '102');
insert into rl_sys_role_menu values ('2', '103');
insert into rl_sys_role_menu values ('2', '104');
insert into rl_sys_role_menu values ('2', '105');
insert into rl_sys_role_menu values ('2', '106');
insert into rl_sys_role_menu values ('2', '107');
insert into rl_sys_role_menu values ('2', '108');
insert into rl_sys_role_menu values ('2', '109');
insert into rl_sys_role_menu values ('2', '110');
insert into rl_sys_role_menu values ('2', '111');
insert into rl_sys_role_menu values ('2', '112');
insert into rl_sys_role_menu values ('2', '113');
insert into rl_sys_role_menu values ('2', '114');
insert into rl_sys_role_menu values ('2', '115');
insert into rl_sys_role_menu values ('2', '500');
insert into rl_sys_role_menu values ('2', '501');
insert into rl_sys_role_menu values ('2', '1000');
insert into rl_sys_role_menu values ('2', '1001');
insert into rl_sys_role_menu values ('2', '1002');
insert into rl_sys_role_menu values ('2', '1003');
insert into rl_sys_role_menu values ('2', '1004');
insert into rl_sys_role_menu values ('2', '1005');
insert into rl_sys_role_menu values ('2', '1006');
insert into rl_sys_role_menu values ('2', '1007');
insert into rl_sys_role_menu values ('2', '1008');
insert into rl_sys_role_menu values ('2', '1009');
insert into rl_sys_role_menu values ('2', '1010');
insert into rl_sys_role_menu values ('2', '1011');
insert into rl_sys_role_menu values ('2', '1012');
insert into rl_sys_role_menu values ('2', '1013');
insert into rl_sys_role_menu values ('2', '1014');
insert into rl_sys_role_menu values ('2', '1015');
insert into rl_sys_role_menu values ('2', '1016');
insert into rl_sys_role_menu values ('2', '1017');
insert into rl_sys_role_menu values ('2', '1018');
insert into rl_sys_role_menu values ('2', '1019');
insert into rl_sys_role_menu values ('2', '1020');
insert into rl_sys_role_menu values ('2', '1021');
insert into rl_sys_role_menu values ('2', '1022');
insert into rl_sys_role_menu values ('2', '1023');
insert into rl_sys_role_menu values ('2', '1024');
insert into rl_sys_role_menu values ('2', '1025');
insert into rl_sys_role_menu values ('2', '1026');
insert into rl_sys_role_menu values ('2', '1027');
insert into rl_sys_role_menu values ('2', '1028');
insert into rl_sys_role_menu values ('2', '1029');
insert into rl_sys_role_menu values ('2', '1030');
insert into rl_sys_role_menu values ('2', '1031');
insert into rl_sys_role_menu values ('2', '1032');
insert into rl_sys_role_menu values ('2', '1033');
insert into rl_sys_role_menu values ('2', '1034');
insert into rl_sys_role_menu values ('2', '1035');
insert into rl_sys_role_menu values ('2', '1036');
insert into rl_sys_role_menu values ('2', '1037');
insert into rl_sys_role_menu values ('2', '1038');
insert into rl_sys_role_menu values ('2', '1039');
insert into rl_sys_role_menu values ('2', '1040');
insert into rl_sys_role_menu values ('2', '1041');
insert into rl_sys_role_menu values ('2', '1042');
insert into rl_sys_role_menu values ('2', '1043');
insert into rl_sys_role_menu values ('2', '1044');
insert into rl_sys_role_menu values ('2', '1045');
insert into rl_sys_role_menu values ('2', '1046');
insert into rl_sys_role_menu values ('2', '1047');
insert into rl_sys_role_menu values ('2', '1048');
insert into rl_sys_role_menu values ('2', '1049');
insert into rl_sys_role_menu values ('2', '1050');
insert into rl_sys_role_menu values ('2', '1051');
insert into rl_sys_role_menu values ('2', '1052');
insert into rl_sys_role_menu values ('2', '1053');
insert into rl_sys_role_menu values ('2', '1054');
insert into rl_sys_role_menu values ('2', '1055');
insert into rl_sys_role_menu values ('2', '1056');
insert into rl_sys_role_menu values ('2', '1057');
insert into rl_sys_role_menu values ('2', '1058');
insert into rl_sys_role_menu values ('2', '1059');
insert into rl_sys_role_menu values ('2', '1060');
insert into rl_sys_role_menu values ('2', '1061');


-- ----------------------------
-- 8、角色和机构关联表  角色1-N机构
-- ----------------------------
create table rl_sys_role_org (
  role_id 	number(20)  not null,
  org_id 	number(20)  not null
);

alter table rl_sys_role_org add constraint pk_rl_sys_role_org primary key (role_id, org_id);

comment on table  rl_sys_role_org              is '角色和机构关联表';
comment on column rl_sys_role_org.role_id      is '角色ID';
comment on column rl_sys_role_org.org_id       is '机构ID';

-- ----------------------------
-- 初始化-角色和机构关联表数据
-- ----------------------------
insert into rl_sys_role_org values ('2', '100');
insert into rl_sys_role_org values ('2', '101');
insert into rl_sys_role_org values ('2', '105');


-- ----------------------------
-- 9、用户与岗位关联表  用户1-N岗位
-- ----------------------------
create table rl_sys_user_post
(
	user_id number(20)  not null,
	post_id number(20)  not null
);

alter table rl_sys_user_post add constraint pk_rl_sys_user_post primary key (user_id, post_id);

comment on table  rl_sys_user_post              is '用户与岗位关联表';
comment on column rl_sys_user_post.user_id      is '用户ID';
comment on column rl_sys_user_post.post_id      is '岗位ID';

-- ----------------------------
-- 初始化-用户与岗位关联表数据
-- ----------------------------
insert into rl_sys_user_post values ('1', '1');
insert into rl_sys_user_post values ('2', '2');


-- ----------------------------
-- 10、操作日志记录
-- ----------------------------
create sequence seq_rl_sys_oper_log
 increment by 1
 start with 100
 nomaxvalue
 nominvalue
 cache 20;

create table rl_sys_oper_log (
  oper_id           number(20)      not null ,
  title             varchar2(50)    default '',
  business_type     number(2)       default 0,
  method            varchar2(100)   default '',
  request_method    varchar(10)     default '',
  operator_type     number(1)       default 0,
  oper_name         varchar2(50)    default '',
  org_name          varchar2(50)    default '',
  oper_url          varchar2(255) 	default '',
  oper_ip           varchar2(50)    default '',
  oper_location     varchar2(255)   default '',
  oper_param        varchar2(2000)  default '',
  json_result       varchar2(2000)  default '',
  status            number(1)       default 0,
  error_msg         varchar2(2000)  default '' ,
  oper_time         date
);

alter table rl_sys_oper_log add constraint pk_rl_sys_oper_log primary key (oper_id);

comment on table  rl_sys_oper_log                is '操作日志记录';
comment on column rl_sys_oper_log.oper_id        is '日志主键seq_rl_sys_oper_log.nextval';
comment on column rl_sys_oper_log.title          is '模块标题';
comment on column rl_sys_oper_log.business_type  is '业务类型（0其它 1新增 2修改 3删除）';
comment on column rl_sys_oper_log.method         is '方法名称';
comment on column rl_sys_oper_log.request_method is '请求方式';
comment on column rl_sys_oper_log.operator_type  is '操作类别（0其它 1后台用户 2手机端用户）';
comment on column rl_sys_oper_log.oper_name      is '操作人员';
comment on column rl_sys_oper_log.org_name       is '机构名称';
comment on column rl_sys_oper_log.oper_url       is '请求URL';
comment on column rl_sys_oper_log.oper_ip        is '主机地址';
comment on column rl_sys_oper_log.oper_location  is '操作地点';
comment on column rl_sys_oper_log.oper_param     is '请求参数';
comment on column rl_sys_oper_log.json_result    is '返回参数';
comment on column rl_sys_oper_log.status         is '操作状态（0正常 1异常）';
comment on column rl_sys_oper_log.error_msg      is '错误消息';
comment on column rl_sys_oper_log.oper_time      is '操作时间';


-- ----------------------------
-- 11、字典类型表
-- ----------------------------
create sequence seq_rl_sys_dict_type
 increment by 1
 start with 100
 nomaxvalue
 nominvalue
 cache 20;

create table rl_sys_dict_type
(
  dict_id           number(20)      not null,
  dict_name         varchar2(100)   default '',
  dict_type         varchar2(100)   default '',
  status            char(1)         default '0',
  create_by         varchar2(64)    default '',
  create_time       date,
  update_by         varchar2(64)    default '',
  update_time       date,
  remark            varchar2(500)   default null
);

alter table rl_sys_dict_type add constraint pk_rl_sys_dict_type primary key (dict_id);
create unique index rl_sys_dict_type_index1 on rl_sys_dict_type (dict_type);

comment on table  rl_sys_dict_type               is '字典类型表';
comment on column rl_sys_dict_type.dict_id       is '字典主键seq_rl_sys_dict_type.nextval';
comment on column rl_sys_dict_type.dict_name     is '字典名称';
comment on column rl_sys_dict_type.dict_type     is '字典类型';
comment on column rl_sys_dict_type.status        is '状态（0正常 1停用）';
comment on column rl_sys_dict_type.create_by     is '创建者';
comment on column rl_sys_dict_type.create_time   is '创建时间';
comment on column rl_sys_dict_type.update_by     is '更新者';
comment on column rl_sys_dict_type.update_time   is '更新时间';
comment on column rl_sys_dict_type.remark        is '备注';

insert into rl_sys_dict_type values(1,  '用户性别', 'sys_user_sex',        '0', 'admin', TO_DATE('2018-03-16 11-33-00', 'YYYY-MM-DD HH24:MI:SS'), 'ry', TO_DATE('2018-03-16 11-33-00', 'YYYY-MM-DD HH24:MI:SS'), '用户性别列表');
insert into rl_sys_dict_type values(2,  '菜单状态', 'sys_show_hide',       '0', 'admin', TO_DATE('2018-03-16 11-33-00', 'YYYY-MM-DD HH24:MI:SS'), 'ry', TO_DATE('2018-03-16 11-33-00', 'YYYY-MM-DD HH24:MI:SS'), '菜单状态列表');
insert into rl_sys_dict_type values(3,  '系统开关', 'sys_normal_disable',  '0', 'admin', TO_DATE('2018-03-16 11-33-00', 'YYYY-MM-DD HH24:MI:SS'), 'ry', TO_DATE('2018-03-16 11-33-00', 'YYYY-MM-DD HH24:MI:SS'), '系统开关列表');
insert into rl_sys_dict_type values(4,  '任务状态', 'sys_job_status',      '0', 'admin', TO_DATE('2018-03-16 11-33-00', 'YYYY-MM-DD HH24:MI:SS'), 'ry', TO_DATE('2018-03-16 11-33-00', 'YYYY-MM-DD HH24:MI:SS'), '任务状态列表');
insert into rl_sys_dict_type values(5,  '任务分组', 'sys_job_group',       '0', 'admin', TO_DATE('2018-03-16 11-33-00', 'YYYY-MM-DD HH24:MI:SS'), 'ry', TO_DATE('2018-03-16 11-33-00', 'YYYY-MM-DD HH24:MI:SS'), '任务分组列表');
insert into rl_sys_dict_type values(6,  '系统是否', 'sys_yes_no',          '0', 'admin', TO_DATE('2018-03-16 11-33-00', 'YYYY-MM-DD HH24:MI:SS'), 'ry', TO_DATE('2018-03-16 11-33-00', 'YYYY-MM-DD HH24:MI:SS'), '系统是否列表');
insert into rl_sys_dict_type values(7,  '通知类型', 'sys_notice_type',     '0', 'admin', TO_DATE('2018-03-16 11-33-00', 'YYYY-MM-DD HH24:MI:SS'), 'ry', TO_DATE('2018-03-16 11-33-00', 'YYYY-MM-DD HH24:MI:SS'), '通知类型列表');
insert into rl_sys_dict_type values(8,  '通知状态', 'sys_notice_status',   '0', 'admin', TO_DATE('2018-03-16 11-33-00', 'YYYY-MM-DD HH24:MI:SS'), 'ry', TO_DATE('2018-03-16 11-33-00', 'YYYY-MM-DD HH24:MI:SS'), '通知状态列表');
insert into rl_sys_dict_type values(9,  '操作类型', 'sys_oper_type',       '0', 'admin', TO_DATE('2018-03-16 11-33-00', 'YYYY-MM-DD HH24:MI:SS'), 'ry', TO_DATE('2018-03-16 11-33-00', 'YYYY-MM-DD HH24:MI:SS'), '操作类型列表');
insert into rl_sys_dict_type values(10, '系统状态', 'sys_common_status',   '0', 'admin', TO_DATE('2018-03-16 11-33-00', 'YYYY-MM-DD HH24:MI:SS'), 'ry', TO_DATE('2018-03-16 11-33-00', 'YYYY-MM-DD HH24:MI:SS'), '登录状态列表');


-- ----------------------------
-- 12、字典数据表
-- ----------------------------
create sequence seq_rl_sys_dict_data
 increment by 1
 start with 100
 nomaxvalue
 nominvalue
 cache 20;

create table rl_sys_dict_data
(
  dict_code        number(20)      not null,
  dict_sort        number(4)       default 0,
  dict_label       varchar2(100)   default '',
  dict_value       varchar2(100)   default '',
  dict_type        varchar2(100)   default '',
  css_class        varchar2(100)   default null,
  list_class       varchar2(100)   default null,
  is_default       char(1)         default 'N',
  status           char(1)         default '0',
  create_by        varchar2(64)    default '',
  create_time      date,
  update_by        varchar2(64)    default '',
  update_time      date,
  remark           varchar2(500)   default null
);

alter table rl_sys_dict_data add constraint pk_rl_sys_dict_data primary key (dict_code);

comment on table  rl_sys_dict_data               is '字典数据表';
comment on column rl_sys_dict_data.dict_code     is '字典主键seq_rl_sys_dict_data.nextval';
comment on column rl_sys_dict_data.dict_sort     is '字典排序';
comment on column rl_sys_dict_data.dict_label    is '字典标签';
comment on column rl_sys_dict_data.dict_value    is '字典键值';
comment on column rl_sys_dict_data.dict_type     is '字典类型';
comment on column rl_sys_dict_data.css_class     is '样式属性（其他样式扩展）';
comment on column rl_sys_dict_data.list_class    is '表格回显样式';
comment on column rl_sys_dict_data.is_default    is '是否默认（Y是 N否）';
comment on column rl_sys_dict_data.status        is '状态（0正常 1停用）';
comment on column rl_sys_dict_data.create_by     is '创建者';
comment on column rl_sys_dict_data.create_time   is '创建时间';
comment on column rl_sys_dict_data.update_by     is '更新者';
comment on column rl_sys_dict_data.update_time   is '更新时间';
comment on column rl_sys_dict_data.remark        is '备注';

insert into rl_sys_dict_data values(1,  1,  '男',       '0',       'sys_user_sex',        '',   '',        'Y', '0', 'admin', TO_DATE('2018-03-16 11-33-00', 'YYYY-MM-DD HH24:MI:SS'), 'ry', TO_DATE('2018-03-16 11-33-00', 'YYYY-MM-DD HH24:MI:SS'), '性别男');
insert into rl_sys_dict_data values(2,  2,  '女',       '1',       'sys_user_sex',        '',   '',        'N', '0', 'admin', TO_DATE('2018-03-16 11-33-00', 'YYYY-MM-DD HH24:MI:SS'), 'ry', TO_DATE('2018-03-16 11-33-00', 'YYYY-MM-DD HH24:MI:SS'), '性别女');
insert into rl_sys_dict_data values(3,  3,  '未知',     '2',       'sys_user_sex',        '',   '',        'N', '0', 'admin', TO_DATE('2018-03-16 11-33-00', 'YYYY-MM-DD HH24:MI:SS'), 'ry', TO_DATE('2018-03-16 11-33-00', 'YYYY-MM-DD HH24:MI:SS'), '性别未知');
insert into rl_sys_dict_data values(4,  1,  '显示',     '0',       'sys_show_hide',       '',   'primary', 'Y', '0', 'admin', TO_DATE('2018-03-16 11-33-00', 'YYYY-MM-DD HH24:MI:SS'), 'ry', TO_DATE('2018-03-16 11-33-00', 'YYYY-MM-DD HH24:MI:SS'), '显示菜单');
insert into rl_sys_dict_data values(5,  2,  '隐藏',     '1',       'sys_show_hide',       '',   'danger',  'N', '0', 'admin', TO_DATE('2018-03-16 11-33-00', 'YYYY-MM-DD HH24:MI:SS'), 'ry', TO_DATE('2018-03-16 11-33-00', 'YYYY-MM-DD HH24:MI:SS'), '隐藏菜单');
insert into rl_sys_dict_data values(6,  1,  '正常',     '0',       'sys_normal_disable',  '',   'primary', 'Y', '0', 'admin', TO_DATE('2018-03-16 11-33-00', 'YYYY-MM-DD HH24:MI:SS'), 'ry', TO_DATE('2018-03-16 11-33-00', 'YYYY-MM-DD HH24:MI:SS'), '正常状态');
insert into rl_sys_dict_data values(7,  2,  '停用',     '1',       'sys_normal_disable',  '',   'danger',  'N', '0', 'admin', TO_DATE('2018-03-16 11-33-00', 'YYYY-MM-DD HH24:MI:SS'), 'ry', TO_DATE('2018-03-16 11-33-00', 'YYYY-MM-DD HH24:MI:SS'), '停用状态');
insert into rl_sys_dict_data values(8,  1,  '正常',     '0',       'sys_job_status',      '',   'primary', 'Y', '0', 'admin', TO_DATE('2018-03-16 11-33-00', 'YYYY-MM-DD HH24:MI:SS'), 'ry', TO_DATE('2018-03-16 11-33-00', 'YYYY-MM-DD HH24:MI:SS'), '正常状态');
insert into rl_sys_dict_data values(9,  2,  '暂停',     '1',       'sys_job_status',      '',   'danger',  'N', '0', 'admin', TO_DATE('2018-03-16 11-33-00', 'YYYY-MM-DD HH24:MI:SS'), 'ry', TO_DATE('2018-03-16 11-33-00', 'YYYY-MM-DD HH24:MI:SS'), '停用状态');
insert into rl_sys_dict_data values(10, 1,  '默认',     'DEFAULT', 'sys_job_group',       '',   '',        'Y', '0', 'admin', TO_DATE('2018-03-16 11-33-00', 'YYYY-MM-DD HH24:MI:SS'), 'ry', TO_DATE('2018-03-16 11-33-00', 'YYYY-MM-DD HH24:MI:SS'), '默认分组');
insert into rl_sys_dict_data values(11, 2,  '系统',     'SYSTEM',  'sys_job_group',       '',   '',        'N', '0', 'admin', TO_DATE('2018-03-16 11-33-00', 'YYYY-MM-DD HH24:MI:SS'), 'ry', TO_DATE('2018-03-16 11-33-00', 'YYYY-MM-DD HH24:MI:SS'), '系统分组');
insert into rl_sys_dict_data values(12, 1,  '是',       'Y',       'sys_yes_no',          '',   'primary', 'Y', '0', 'admin', TO_DATE('2018-03-16 11-33-00', 'YYYY-MM-DD HH24:MI:SS'), 'ry', TO_DATE('2018-03-16 11-33-00', 'YYYY-MM-DD HH24:MI:SS'), '系统默认是');
insert into rl_sys_dict_data values(13, 2,  '否',       'N',       'sys_yes_no',          '',   'danger',  'N', '0', 'admin', TO_DATE('2018-03-16 11-33-00', 'YYYY-MM-DD HH24:MI:SS'), 'ry', TO_DATE('2018-03-16 11-33-00', 'YYYY-MM-DD HH24:MI:SS'), '系统默认否');
insert into rl_sys_dict_data values(14, 1,  '通知',     '1',       'sys_notice_type',     '',   'warning', 'Y', '0', 'admin', TO_DATE('2018-03-16 11-33-00', 'YYYY-MM-DD HH24:MI:SS'), 'ry', TO_DATE('2018-03-16 11-33-00', 'YYYY-MM-DD HH24:MI:SS'), '通知');
insert into rl_sys_dict_data values(15, 2,  '公告',     '2',       'sys_notice_type',     '',   'success', 'N', '0', 'admin', TO_DATE('2018-03-16 11-33-00', 'YYYY-MM-DD HH24:MI:SS'), 'ry', TO_DATE('2018-03-16 11-33-00', 'YYYY-MM-DD HH24:MI:SS'), '公告');
insert into rl_sys_dict_data values(16, 1,  '正常',     '0',       'sys_notice_status',   '',   'primary', 'Y', '0', 'admin', TO_DATE('2018-03-16 11-33-00', 'YYYY-MM-DD HH24:MI:SS'), 'ry', TO_DATE('2018-03-16 11-33-00', 'YYYY-MM-DD HH24:MI:SS'), '正常状态');
insert into rl_sys_dict_data values(17, 2,  '关闭',     '1',       'sys_notice_status',   '',   'danger',  'N', '0', 'admin', TO_DATE('2018-03-16 11-33-00', 'YYYY-MM-DD HH24:MI:SS'), 'ry', TO_DATE('2018-03-16 11-33-00', 'YYYY-MM-DD HH24:MI:SS'), '关闭状态');
insert into rl_sys_dict_data values(18, 99, '其他',     '0',       'sys_oper_type',       '',   'info',    'N', '0', 'admin', TO_DATE('2018-03-16 11-33-00', 'YYYY-MM-DD HH24:MI:SS'), 'ry', TO_DATE('2018-03-16 11-33-00', 'YYYY-MM-DD HH24:MI:SS'), '其他操作');
insert into rl_sys_dict_data values(19, 1,  '新增',     '1',       'sys_oper_type',       '',   'info',    'N', '0', 'admin', TO_DATE('2018-03-16 11-33-00', 'YYYY-MM-DD HH24:MI:SS'), 'ry', TO_DATE('2018-03-16 11-33-00', 'YYYY-MM-DD HH24:MI:SS'), '新增操作');
insert into rl_sys_dict_data values(20, 2,  '修改',     '2',       'sys_oper_type',       '',   'info',    'N', '0', 'admin', TO_DATE('2018-03-16 11-33-00', 'YYYY-MM-DD HH24:MI:SS'), 'ry', TO_DATE('2018-03-16 11-33-00', 'YYYY-MM-DD HH24:MI:SS'), '修改操作');
insert into rl_sys_dict_data values(21, 3,  '删除',     '3',       'sys_oper_type',       '',   'danger',  'N', '0', 'admin', TO_DATE('2018-03-16 11-33-00', 'YYYY-MM-DD HH24:MI:SS'), 'ry', TO_DATE('2018-03-16 11-33-00', 'YYYY-MM-DD HH24:MI:SS'), '删除操作');
insert into rl_sys_dict_data values(22, 4,  '授权',     '4',       'sys_oper_type',       '',   'primary', 'N', '0', 'admin', TO_DATE('2018-03-16 11-33-00', 'YYYY-MM-DD HH24:MI:SS'), 'ry', TO_DATE('2018-03-16 11-33-00', 'YYYY-MM-DD HH24:MI:SS'), '授权操作');
insert into rl_sys_dict_data values(23, 5,  '导出',     '5',       'sys_oper_type',       '',   'warning', 'N', '0', 'admin', TO_DATE('2018-03-16 11-33-00', 'YYYY-MM-DD HH24:MI:SS'), 'ry', TO_DATE('2018-03-16 11-33-00', 'YYYY-MM-DD HH24:MI:SS'), '导出操作');
insert into rl_sys_dict_data values(24, 6,  '导入',     '6',       'sys_oper_type',       '',   'warning', 'N', '0', 'admin', TO_DATE('2018-03-16 11-33-00', 'YYYY-MM-DD HH24:MI:SS'), 'ry', TO_DATE('2018-03-16 11-33-00', 'YYYY-MM-DD HH24:MI:SS'), '导入操作');
insert into rl_sys_dict_data values(25, 7,  '强退',     '7',       'sys_oper_type',       '',   'danger',  'N', '0', 'admin', TO_DATE('2018-03-16 11-33-00', 'YYYY-MM-DD HH24:MI:SS'), 'ry', TO_DATE('2018-03-16 11-33-00', 'YYYY-MM-DD HH24:MI:SS'), '强退操作');
insert into rl_sys_dict_data values(26, 8,  '生成代码', '8',       'sys_oper_type',       '',   'warning', 'N', '0', 'admin', TO_DATE('2018-03-16 11-33-00', 'YYYY-MM-DD HH24:MI:SS'), 'ry', TO_DATE('2018-03-16 11-33-00', 'YYYY-MM-DD HH24:MI:SS'), '生成操作');
insert into rl_sys_dict_data values(27, 9,  '清空数据', '9',       'sys_oper_type',       '',   'danger',  'N', '0', 'admin', TO_DATE('2018-03-16 11-33-00', 'YYYY-MM-DD HH24:MI:SS'), 'ry', TO_DATE('2018-03-16 11-33-00', 'YYYY-MM-DD HH24:MI:SS'), '清空操作');
insert into rl_sys_dict_data values(28, 1,  '成功',     '0',       'sys_common_status',   '',   'primary', 'N', '0', 'admin', TO_DATE('2018-03-16 11-33-00', 'YYYY-MM-DD HH24:MI:SS'), 'ry', TO_DATE('2018-03-16 11-33-00', 'YYYY-MM-DD HH24:MI:SS'), '正常状态');
insert into rl_sys_dict_data values(29, 2,  '失败',     '1',       'sys_common_status',   '',   'danger',  'N', '0', 'admin', TO_DATE('2018-03-16 11-33-00', 'YYYY-MM-DD HH24:MI:SS'), 'ry', TO_DATE('2018-03-16 11-33-00', 'YYYY-MM-DD HH24:MI:SS'), '停用状态');


-- ----------------------------
-- 13、参数配置表
-- ----------------------------
create sequence seq_rl_sys_config
 increment by 1
 start with 100
 nomaxvalue
 nominvalue
 cache 20;

create table rl_sys_config (
  config_id         number(20)     not null,
  config_name       varchar2(100)  default '',
  config_key        varchar2(100)  default '',
  config_value      varchar2(100)  default '',
  config_type       char(1)        default 'N',
  create_by         varchar2(64)   default '',
  create_time       date,
  update_by         varchar2(64)   default '',
  update_time       date,
  remark            varchar2(500)  default null
);
alter table rl_sys_config add constraint pk_rl_sys_config primary key (config_id);

comment on table  rl_sys_config               is '参数配置表';
comment on column rl_sys_config.config_id     is '参数主键seq_rl_sys_config.nextval';
comment on column rl_sys_config.config_name   is '参数名称';
comment on column rl_sys_config.config_key    is '参数键名';
comment on column rl_sys_config.config_value  is '参数键值';
comment on column rl_sys_config.config_type   is '系统内置（Y是 N否）';
comment on column rl_sys_config.create_by     is '创建者';
comment on column rl_sys_config.create_time   is '创建时间';
comment on column rl_sys_config.update_by     is '更新者';
comment on column rl_sys_config.update_time   is '更新时间';
comment on column rl_sys_config.remark        is '备注';

insert into rl_sys_config values(1, '主框架页-默认皮肤样式名称',     'sys.index.skinName',       'skin-blue',     'Y', 'admin', TO_DATE('2018-03-16 11-33-00', 'YYYY-MM-DD HH24:MI:SS'), 'ry', TO_DATE('2018-03-16 11-33-00', 'YYYY-MM-DD HH24:MI:SS'), '蓝色 skin-blue、绿色 skin-green、紫色 skin-purple、红色 skin-red、黄色 skin-yellow');
insert into rl_sys_config values(2, '用户管理-账号初始密码',         'sys.user.initPassword',    '123456',        'Y', 'admin', TO_DATE('2018-03-16 11-33-00', 'YYYY-MM-DD HH24:MI:SS'), 'ry', TO_DATE('2018-03-16 11-33-00', 'YYYY-MM-DD HH24:MI:SS'), '初始化密码 123456');
insert into rl_sys_config values(3, '主框架页-侧边栏主题',           'sys.index.sideTheme',      'theme-dark',    'Y', 'admin', TO_DATE('2018-03-16 11-33-00', 'YYYY-MM-DD HH24:MI:SS'), 'ry', TO_DATE('2018-03-16 11-33-00', 'YYYY-MM-DD HH24:MI:SS'), '深黑主题theme-dark，浅色主题theme-light，深蓝主题theme-blue');
insert into rl_sys_config values(4, '账号自助-是否开启用户注册功能', 'sys.account.registerUser', 'false',         'Y', 'admin', TO_DATE('2018-03-16 11-33-00', 'YYYY-MM-DD HH24:MI:SS'), 'ry', TO_DATE('2018-03-16 11-33-00', 'YYYY-MM-DD HH24:MI:SS'), '是否开启注册用户功能');


-- ----------------------------
-- 14、系统访问记录
-- ----------------------------
create sequence seq_rl_sys_logininfor
 increment by 1
 start with 100
 nomaxvalue
 nominvalue
 cache 20;

create table rl_sys_logininfor (
  info_id         number(20)     not null,
  login_name       varchar2(50)   default '',
  ipaddr          varchar2(50)   default '',
  login_location  varchar2(255)  default '',
  browser         varchar2(50)   default '',
  os              varchar2(50)   default '',
  status          char(1)        default '0',
  msg             varchar2(255)  default '',
  login_time      date
);

alter table rl_sys_logininfor add constraint pk_rl_sys_logininfor primary key (info_id);

comment on table  rl_sys_logininfor                is '系统访问记录';
comment on column rl_sys_logininfor.info_id        is '访问主键seq_rl_sys_logininfor.nextval';
comment on column rl_sys_logininfor.login_name     is '登录账号';
comment on column rl_sys_logininfor.ipaddr         is '登录IP地址';
comment on column rl_sys_logininfor.login_location is '登录地点';
comment on column rl_sys_logininfor.browser        is '浏览器类型';
comment on column rl_sys_logininfor.os             is '操作系统';
comment on column rl_sys_logininfor.status         is '登录状态（0成功 1失败）';
comment on column rl_sys_logininfor.msg            is '提示消息';
comment on column rl_sys_logininfor.login_time     is '访问时间';


-- ----------------------------
-- 15、在线用户记录
-- ----------------------------
create sequence seq_rl_sys_user_online
 increment by 1
 start with 100
 nomaxvalue
 nominvalue
 cache 20;

create table rl_sys_user_online (
  sessionId        varchar2(50)   default '',
  login_name       varchar2(50)   default '',
  org_name         varchar2(50)   default '',
  ipaddr           varchar2(50)   default '',
  login_location   varchar2(255)  default '',
  browser          varchar2(50)   default '',
  os               varchar2(50)   default '',
  status           varchar2(50)   default '',
  start_timestamp  date,
  last_access_time date,
  expire_time      number(10)      default 0
);

alter table rl_sys_user_online add constraint pk_rl_sys_user_online primary key (sessionId);

comment on table  rl_sys_user_online                  is '在线用户记录';
comment on column rl_sys_user_online.sessionId        is '访问主键seq_rl_sys_user_online.nextval';
comment on column rl_sys_user_online.login_name       is '登录账号';
comment on column rl_sys_user_online.org_name         is '机构名称';
comment on column rl_sys_user_online.ipaddr           is '登录IP地址';
comment on column rl_sys_user_online.login_location   is '登录地址';
comment on column rl_sys_user_online.browser          is '浏览器类型';
comment on column rl_sys_user_online.os               is '操作系统';
comment on column rl_sys_user_online.status           is '在线状态on_line在线off_line离线';
comment on column rl_sys_user_online.start_timestamp  is 'session创建时间';
comment on column rl_sys_user_online.last_access_time is 'session最后访问时间';
comment on column rl_sys_user_online.expire_time      is '超时时间，单位为分钟';


-- ----------------------------
-- 16、定时任务调度表
-- ----------------------------
create sequence seq_rl_sys_job
 increment by 1
 start with 100
 nomaxvalue
 nominvalue
 cache 20;

create table rl_sys_job (
  job_id              number(20)     not null,
  job_name            varchar2(64)   default '',
  job_group           varchar2(64)   default '',
  invoke_target       varchar2(500)  not null ,
  cron_expression     varchar2(255)  default '',
  misfire_policy      varchar2(20)   default '3',
  concurrent          char(1)        default '1',
  status              char(1)        default '0',
  create_by           varchar2(64)   default '',
  create_time         date,
  update_by           varchar2(64)   default '',
  update_time         date,
  remark              varchar2(500)  default ''
);

alter table rl_sys_job add constraint pk_rl_sys_job primary key (job_id, job_name, job_group);

comment on table  rl_sys_job                   is '定时任务调度表';
comment on column rl_sys_job.job_id            is '任务主键seq_rl_sys_job.nextval';
comment on column rl_sys_job.job_name          is '任务名称';
comment on column rl_sys_job.job_group         is '任务组名';
comment on column rl_sys_job.invoke_target     is '调用目标字符串';
comment on column rl_sys_job.cron_expression   is 'cron执行表达式';
comment on column rl_sys_job.misfire_policy    is '计划执行错误策略（1立即执行 2执行一次 3放弃执行）';
comment on column rl_sys_job.concurrent        is '是否并发执行（0允许 1禁止）';
comment on column rl_sys_job.status            is '状态（0正常 1暂停）';
comment on column rl_sys_job.create_by         is '创建者';
comment on column rl_sys_job.create_time       is '创建时间';
comment on column rl_sys_job.update_by         is '更新者';
comment on column rl_sys_job.update_time       is '更新时间';
comment on column rl_sys_job.remark            is '备注信息';

insert into rl_sys_job values(1, '系统默认（无参）', 'DEFAULT', 'ryTask.ryNoParams',        '0/10 * * * * ?', '3', '1', '1', 'admin', TO_DATE('2018-03-16 11-33-00', 'YYYY-MM-DD HH24:MI:SS'), 'ry', TO_DATE('2018-03-16 11-33-00', 'YYYY-MM-DD HH24:MI:SS'), '');
insert into rl_sys_job values(2, '系统默认（有参）', 'DEFAULT', 'ryTask.ryParams(''ry'')',  '0/15 * * * * ?', '3', '1', '1', 'admin', TO_DATE('2018-03-16 11-33-00', 'YYYY-MM-DD HH24:MI:SS'), 'ry', TO_DATE('2018-03-16 11-33-00', 'YYYY-MM-DD HH24:MI:SS'), '');
insert into rl_sys_job values(3, '系统默认（多参）', 'DEFAULT', 'ryTask.ryMultipleParams(''ry'', true, 2000L, 316.50D, 100)',  '0/20 * * * * ?', '3', '1', '1', 'admin', TO_DATE('2018-03-16 11-33-00', 'YYYY-MM-DD HH24:MI:SS'), 'ry', TO_DATE('2018-03-16 11-33-00', 'YYYY-MM-DD HH24:MI:SS'), '');


-- ----------------------------
-- 17、定时任务调度日志表
-- ----------------------------
create sequence seq_rl_sys_job_log
 increment by 1
 start with 1
 nomaxvalue
 nominvalue
 cache 20;

create table rl_sys_job_log (
  job_log_id          number(20)       not null,
  job_name            varchar2(64)     not null,
  job_group           varchar2(64)     not null,
  invoke_target       varchar2(500)    not null ,
  job_message         varchar2(500),
  status              char(1)          default '0',
  exception_info      varchar2(2000)   default '',
  create_time         date
);

alter table rl_sys_job_log add constraint pk_rl_sys_job_log primary key (job_log_id);

comment on table  rl_sys_job_log                   is '定时任务调度日志表';
comment on column rl_sys_job_log.job_log_id        is '日志主键seq_rl_sys_job_log.nextval';
comment on column rl_sys_job_log.job_name          is '任务名称';
comment on column rl_sys_job_log.job_group         is '任务组名';
comment on column rl_sys_job_log.invoke_target     is '调用目标字符串';
comment on column rl_sys_job_log.job_message       is '日志信息';
comment on column rl_sys_job_log.status            is '执行状态（0正常 1失败）';
comment on column rl_sys_job_log.exception_info    is '异常信息';
comment on column rl_sys_job_log.create_time       is '创建时间';


-- ----------------------------
-- 18、通知公告表
-- ----------------------------
create sequence seq_rl_sys_notice
 increment by 1
 start with 100
 nomaxvalue
 nominvalue
 cache 20;

create table rl_sys_notice (
  notice_id         number(20)      not null,
  notice_title      varchar2(50)    not null,
  notice_type       char(1)         not null,
  notice_content    varchar2(2000)  default null,
  status            char(1)         default '0',
  create_by         varchar2(64)    default '',
  create_time       date,
  update_by         varchar2(64)    default '',
  update_time       date,
  remark            varchar2(255)   default null
);

alter table rl_sys_notice add constraint pk_rl_sys_notice primary key (notice_id);

comment on table  rl_sys_notice                   is '通知公告表';
comment on column rl_sys_notice.notice_id         is '公告主键seq_rl_sys_notice.nextval';
comment on column rl_sys_notice.notice_title      is '公告标题';
comment on column rl_sys_notice.notice_type       is '公告类型（1通知 2公告）';
comment on column rl_sys_notice.notice_content    is '公告内容';
comment on column rl_sys_notice.status            is '公告状态（0正常 1关闭）';
comment on column rl_sys_notice.create_by         is '创建者';
comment on column rl_sys_notice.create_time       is '创建时间';
comment on column rl_sys_notice.update_by         is '更新者';
comment on column rl_sys_notice.update_time       is '更新时间';
comment on column rl_sys_notice.remark            is '备注';

-- ----------------------------
-- 初始化-公告信息表数据
-- ----------------------------
insert into rl_sys_notice values('1', '温馨提醒：2018-07-01 若依新版本发布啦', '2', '新版本内容', '0', 'admin', TO_DATE('2018-03-16 11-33-00', 'YYYY-MM-DD HH24:MI:SS'), 'ry', TO_DATE('2018-03-16 11-33-00', 'YYYY-MM-DD HH24:MI:SS'), '管理员');
insert into rl_sys_notice values('2', '维护通知：2018-07-01 若依系统凌晨维护', '1', '维护内容',   '0', 'admin', TO_DATE('2018-03-16 11-33-00', 'YYYY-MM-DD HH24:MI:SS'), 'ry', TO_DATE('2018-03-16 11-33-00', 'YYYY-MM-DD HH24:MI:SS'), '管理员');
commit;


-- ----------------------------
-- 19、代码生成业务表
-- ----------------------------
create sequence seq_rl_gen_table
    increment by 1
    start with 100
    nomaxvalue
    nominvalue
    cache 20;

create table rl_gen_table (
  table_id             number(20)       not null,
  table_name           varchar2(200)    default '',
  table_comment        varchar2(500)    default '',
  sub_table_name       varchar2(64)     default null,
  sub_table_fk_name    varchar2(64)     default null,
  class_name           varchar2(100)    default '',
  tpl_category         varchar2(200)    default 'crud',
  package_name         varchar2(100),
  module_name          varchar2(30),
  business_name        varchar2(30),
  function_name        varchar2(50),
  function_author      varchar2(50),
  options              varchar2(1000),
  create_by            varchar2(64)     default '',
  create_time          date,
  update_by            varchar2(64)     default '',
  update_time          date,
  remark               varchar2(500)    default null
);

alter table rl_gen_table add constraint pk_rl_gen_table primary key (table_id);

comment on table  rl_gen_table                   is '代码生成业务表';
comment on column rl_gen_table.table_id          is '主键seq_rl_gen_table.nextval';
comment on column rl_gen_table.table_name        is '表名称';
comment on column rl_gen_table.table_comment     is '表描述';
comment on column rl_gen_table.sub_table_name    is '关联子表的表名';
comment on column rl_gen_table.sub_table_fk_name is '子表关联的外键名';
comment on column rl_gen_table.class_name        is '实体类名称';
comment on column rl_gen_table.tpl_category      is '使用的模板（crud单表操作 tree树表操作 sub主子表操作）';
comment on column rl_gen_table.package_name      is '生成包路径';
comment on column rl_gen_table.module_name       is '生成模块名';
comment on column rl_gen_table.business_name     is '生成业务名';
comment on column rl_gen_table.function_name     is '生成功能名';
comment on column rl_gen_table.function_author   is '生成功能作者';
comment on column rl_gen_table.options           is '其它生成选项';
comment on column rl_gen_table.create_by         is '创建者';
comment on column rl_gen_table.create_time       is '创建时间';
comment on column rl_gen_table.update_by         is '更新者';
comment on column rl_gen_table.update_time       is '更新时间';
comment on column rl_gen_table.remark            is '备注';


-- ----------------------------
-- 20、代码生成业务表字段
-- ----------------------------
create sequence seq_rl_gen_table_column
    increment by 1
    start with 100
    nomaxvalue
    nominvalue
    cache 20;

create table rl_gen_table_column (
  column_id             number(20)       not null,
  table_id              varchar2(64),
  column_name           varchar2(200) ,
  column_comment        varchar2(500),
  column_type           varchar2(100),
  java_type             varchar2(500),
  java_field            varchar2(200),
  is_pk                 char(1),
  is_increment          char(1),
  is_required           char(1),
  is_insert             char(1),
  is_edit               char(1),
  is_list               char(1),
  is_query              char(1),
  query_type            varchar2(200)    default 'EQ',
  html_type             varchar2(200),
  dict_type             varchar2(200)    default '',
  sort                  number(4),
  create_by             varchar2(64)     default '',
  create_time           date,
  update_by             varchar2(64)     default '',
  update_time           date
);

alter table rl_gen_table_column add constraint pk_rl_gen_table_column primary key (column_id);

comment on table  rl_gen_table_column                   is '代码生成业务表字段';
comment on column rl_gen_table_column.column_id         is '主键seq_rl_gen_table_column.nextval';
comment on column rl_gen_table_column.table_id          is '归属表编号';
comment on column rl_gen_table_column.column_name       is '列名称';
comment on column rl_gen_table_column.column_comment    is '列描述';
comment on column rl_gen_table_column.column_type       is '列类型';
comment on column rl_gen_table_column.java_type         is 'AVA类型';
comment on column rl_gen_table_column.java_field        is 'JAVA字段名';
comment on column rl_gen_table_column.is_pk             is '是否主键（1是）';
comment on column rl_gen_table_column.is_increment      is '是否自增（1是）';
comment on column rl_gen_table_column.is_required       is '是否必填（1是）';
comment on column rl_gen_table_column.is_insert         is '是否为插入字段（1是）';
comment on column rl_gen_table_column.is_edit           is '是否编辑字段（1是）';
comment on column rl_gen_table_column.is_list           is '是否列表字段（1是）';
comment on column rl_gen_table_column.is_query          is '是否查询字段（1是）';
comment on column rl_gen_table_column.query_type        is '查询方式（等于、不等于、大于、小于、范围）';
comment on column rl_gen_table_column.html_type         is '显示类型（文本框、文本域、下拉框、复选框、单选框、日期控件）';
comment on column rl_gen_table_column.dict_type         is '字典类型';
comment on column rl_gen_table_column.sort              is '排序';
comment on column rl_gen_table_column.create_by         is '创建者';
comment on column rl_gen_table_column.create_time       is '创建时间';
comment on column rl_gen_table_column.update_by         is '更新者';
comment on column rl_gen_table_column.update_time       is '更新时间';



/*==============================================================*/
/* Table: RL_INSPEC_ITEM       检验项目表                        */
/*==============================================================*/
create table RL_INSPEC_ITEM
(
    item_id       VARCHAR2(32) not null,
    item_code     VARCHAR2(32) not null,
    item_name     VARCHAR2(50) not null,
    mnemonic_code VARCHAR2(20),
    org_id        NUMBER(20),
    enable        CHAR(1) default 1,
    item_price    NUMBER(6,2),
    remark        VARCHAR2(100),
    fix_item_id   VARCHAR2(32),
    create_by     VARCHAR2(64) default '',
    create_time   DATE,
    update_by     VARCHAR2(64) default '',
    update_time   DATE
)
    tablespace REGIONLIS
    pctfree 10
    initrans 1
    maxtrans 255;
-- Add comments to the table
comment on table RL_INSPEC_ITEM
    is '检验项目表';
-- Add comments to the columns
comment on column RL_INSPEC_ITEM.item_id
    is '项目ID 主键';
comment on column RL_INSPEC_ITEM.item_code
    is '项目代码';
comment on column RL_INSPEC_ITEM.item_name
    is '项目名称';
comment on column RL_INSPEC_ITEM.mnemonic_code
    is '助记码';
comment on column RL_INSPEC_ITEM.org_id
    is '机构ID';
comment on column RL_INSPEC_ITEM.enable
    is '1-启用;0-停用';
comment on column RL_INSPEC_ITEM.item_price
    is '项目价格';
comment on column RL_INSPEC_ITEM.remark
    is '备注';
comment on column RL_INSPEC_ITEM.fix_item_id
    is '对码项目ID';
comment on column RL_INSPEC_ITEM.create_by
    is '创建者';
comment on column RL_INSPEC_ITEM.create_time
    is '创建时间';
comment on column RL_INSPEC_ITEM.update_by
    is '更新者';
comment on column RL_INSPEC_ITEM.update_time
    is '更新时间';
-- Create/Recreate primary, unique and foreign key constraints
alter table RL_INSPEC_ITEM
    add constraint PK_RL_INSPEC_ITEM primary key (ITEM_ID)
        using index
            tablespace REGIONLIS
            pctfree 10
            initrans 2
            maxtrans 255
            storage
            (
            initial 64K
            next 1M
            minextents 1
            maxextents unlimited
            );
alter table RL_INSPEC_ITEM
    add constraint UK_RL_INSPEC_ITEM unique (ITEM_CODE, ORG_ID)
        using index
            tablespace REGIONLIS
            pctfree 10
            initrans 2
            maxtrans 255
            storage
            (
            initial 64K
            next 1M
            minextents 1
            maxextents unlimited
            );

/*==============================================================*/
/* Table: RL_INSPEC_REQUISITION  检验申请表                      */
/*==============================================================*/
-- Create table
create table RL_INSPEC_REQUISITION
(
    id                VARCHAR2(32) not null,
    app_no            VARCHAR2(32) not null,
    app_type          VARCHAR2(10) default '区域LIS',
    app_time          VARCHAR2(20),
    app_status        NUMBER(2),
    app_org_code      VARCHAR2(32) not null,
    app_org_name      VARCHAR2(50) not null,
    app_doc_code      VARCHAR2(32),
    app_doc_name      VARCHAR2(20),
    app_org_phone     VARCHAR2(20),
    app_amount        NUMBER(7,2),
    service_org_code  VARCHAR2(32),
    service_org_name  VARCHAR2(50),
    patient_id        VARCHAR2(32),
    patient_name      VARCHAR2(10) not null,
    patient_idno      VARCHAR2(18),
    patient_age       VARCHAR2(10),
    patient_sex       VARCHAR2(4),
    patient_phone     VARCHAR2(20),
    patient_type      VARCHAR2(5),
    patient_code      VARCHAR2(32),
    patient_diagnosis VARCHAR2(200),
    create_by         VARCHAR2(20) default '',
    create_time       DATE,
    update_by         VARCHAR2(20) default '',
    update_time       DATE
)
    tablespace REGIONLIS
    pctfree 10
    initrans 1
    maxtrans 255
    storage
(
    initial 64K
    minextents 1
    maxextents unlimited
);
-- Add comments to the table
comment on table RL_INSPEC_REQUISITION
    is '检验申请表';
-- Add comments to the columns
comment on column RL_INSPEC_REQUISITION.id
    is '主键ID';
comment on column RL_INSPEC_REQUISITION.app_no
    is '申请单号';
comment on column RL_INSPEC_REQUISITION.app_type
    is '申请单类型';
comment on column RL_INSPEC_REQUISITION.app_time
    is '申请时间';
comment on column RL_INSPEC_REQUISITION.app_status
    is '0：编辑  1：申请中   2：取消申请';
comment on column RL_INSPEC_REQUISITION.app_org_code
    is '申请机构编码';
comment on column RL_INSPEC_REQUISITION.app_org_name
    is '申请机构名称';
comment on column RL_INSPEC_REQUISITION.app_doc_code
    is '申请医生编码';
comment on column RL_INSPEC_REQUISITION.app_doc_name
    is '申请医生名称';
comment on column RL_INSPEC_REQUISITION.app_org_phone
    is '申请机构电话';
comment on column RL_INSPEC_REQUISITION.app_amount
    is '订单总金额';
comment on column RL_INSPEC_REQUISITION.service_org_code
    is '服务机构编码';
comment on column RL_INSPEC_REQUISITION.service_org_name
    is '服务机构名称';
comment on column RL_INSPEC_REQUISITION.patient_id
    is '患者ID';
comment on column RL_INSPEC_REQUISITION.patient_name
    is '患者姓名';
comment on column RL_INSPEC_REQUISITION.patient_idno
    is '患者身份证号';
comment on column RL_INSPEC_REQUISITION.patient_age
    is '患者年龄';
comment on column RL_INSPEC_REQUISITION.patient_sex
    is '患者性别';
comment on column RL_INSPEC_REQUISITION.patient_phone
    is '患者电话';
comment on column RL_INSPEC_REQUISITION.patient_type
    is '患者类型 OP门诊 IP住院';
comment on column RL_INSPEC_REQUISITION.patient_code
    is '就诊号';
comment on column RL_INSPEC_REQUISITION.patient_diagnosis
    is '初步诊断';
comment on column RL_INSPEC_REQUISITION.create_by
    is '创建者';
comment on column RL_INSPEC_REQUISITION.create_time
    is '创建时间';
comment on column RL_INSPEC_REQUISITION.update_by
    is '更新者';
comment on column RL_INSPEC_REQUISITION.update_time
    is '更新时间';
-- Create/Recreate primary, unique and foreign key constraints
alter table RL_INSPEC_REQUISITION
    add constraint PK_RL_INSPEC_REQUISITION primary key (ID)
        using index
            tablespace REGIONLIS
            pctfree 10
            initrans 2
            maxtrans 255;
alter table RL_INSPEC_REQUISITION
    add constraint UK_RL_INSPEC_REQUISITION unique (APP_NO, APP_ORG_CODE)
        using index
            tablespace REGIONLIS
            pctfree 10
            initrans 2
            maxtrans 255;

/*==============================================================*/
/* Table: RL_INSPEC_REQUISITION_ITEMS  检验申请项目表            */
/*==============================================================*/
-- Create table
create table RL_INSPEC_REQUISITION_ITEMS
(
    id             VARCHAR2(32) not null,
    requisition_id VARCHAR2(32) not null,
    item_code      VARCHAR2(32) not null,
    item_name      VARCHAR2(100),
    item_price     NUMBER(6,2) default 0.00
)
    tablespace REGIONLIS
    pctfree 10
    initrans 1
    maxtrans 255
    storage
(
    initial 64K
    minextents 1
    maxextents unlimited
);
-- Add comments to the table
comment on table RL_INSPEC_REQUISITION_ITEMS
    is '检验申请项目表';
-- Add comments to the columns
comment on column RL_INSPEC_REQUISITION_ITEMS.id
    is '主键ID';
comment on column RL_INSPEC_REQUISITION_ITEMS.requisition_id
    is '申请单ID';
comment on column RL_INSPEC_REQUISITION_ITEMS.item_code
    is '申请项目编码';
comment on column RL_INSPEC_REQUISITION_ITEMS.item_name
    is '申请项目名称';
comment on column RL_INSPEC_REQUISITION_ITEMS.item_price
    is '项目价格';
-- Create/Recreate primary, unique and foreign key constraints
alter table RL_INSPEC_REQUISITION_ITEMS
    add constraint PK_RL_INSPEC_REQUISITION_ITEMS primary key (ID)
        using index
            tablespace REGIONLIS
            pctfree 10
            initrans 2
            maxtrans 255;


/*==============================================================*/
/* Table: RL_INSPEC_REQUISITION_BARCODE  检验申请条码表            */
/*==============================================================*/
-- Create table
create table RL_INSPEC_REQUISITION_BARCODE
(
    id                    VARCHAR2(32) not null,
    requisition_id        VARCHAR2(32) not null,
    barcode               VARCHAR2(16) not null,
    patient_id            VARCHAR2(32),
    patient_name          VARCHAR2(10),
    patient_type          VARCHAR2(10),
    patient_sex           VARCHAR2(4),
    patient_code          VARCHAR2(32),
    patient_age           VARCHAR2(10),
    items                 VARCHAR2(200),
    excute_section        VARCHAR2(20),
    report_place          VARCHAR2(200),
    report_time           VARCHAR2(200),
    sample_collect_date   VARCHAR2(100),
    sample_collect_advice VARCHAR2(200),
    sample_state          VARCHAR2(2),
    print_time            VARCHAR2(30),
    remark                VARCHAR2(50)
)
    tablespace REGIONLIS
    pctfree 10
    initrans 1
    maxtrans 255
    storage
(
    initial 64K
    minextents 1
    maxextents unlimited
);
-- Add comments to the table
comment on table RL_INSPEC_REQUISITION_BARCODE
    is '检验申请单条码';
-- Add comments to the columns
comment on column RL_INSPEC_REQUISITION_BARCODE.id
    is '主键ID';
comment on column RL_INSPEC_REQUISITION_BARCODE.requisition_id
    is '申请单ID';
comment on column RL_INSPEC_REQUISITION_BARCODE.barcode
    is '条码号';
comment on column RL_INSPEC_REQUISITION_BARCODE.patient_id
    is '病人ID';
comment on column RL_INSPEC_REQUISITION_BARCODE.patient_name
    is '病人姓名';
comment on column RL_INSPEC_REQUISITION_BARCODE.patient_type
    is '病人类型';
comment on column RL_INSPEC_REQUISITION_BARCODE.patient_sex
    is '病人性别';
comment on column RL_INSPEC_REQUISITION_BARCODE.patient_code
    is '病人号';
comment on column RL_INSPEC_REQUISITION_BARCODE.patient_age
    is '病人年龄';
comment on column RL_INSPEC_REQUISITION_BARCODE.items
    is '项目名称';
comment on column RL_INSPEC_REQUISITION_BARCODE.excute_section
    is '执行科室';
comment on column RL_INSPEC_REQUISITION_BARCODE.report_place
    is '报告地点';
comment on column RL_INSPEC_REQUISITION_BARCODE.report_time
    is '报告时间';
comment on column RL_INSPEC_REQUISITION_BARCODE.sample_collect_date
    is '样本采集时间';
comment on column RL_INSPEC_REQUISITION_BARCODE.sample_collect_advice
    is '样本采集要求';
comment on column RL_INSPEC_REQUISITION_BARCODE.sample_state
    is '样本状态';
comment on column RL_INSPEC_REQUISITION_BARCODE.print_time
    is '条码打印时间';
comment on column RL_INSPEC_REQUISITION_BARCODE.remark
    is '备注';
-- Create/Recreate primary, unique and foreign key constraints
alter table RL_INSPEC_REQUISITION_BARCODE
    add constraint PK_RL_INSPEC_REQ_BARCODE primary key (ID)
        using index
            tablespace REGIONLIS
            pctfree 10
            initrans 2
            maxtrans 255;
alter table RL_INSPEC_REQUISITION_BARCODE
    add constraint UK_RL_INSPEC_REQ_BARCODE unique (REQUISITION_ID, BARCODE)
        using index
            tablespace REGIONLIS
            pctfree 10
            initrans 2
            maxtrans 255;


-- Create table
create table TB_PRINT_TEMPLATE
(
    id          INTEGER not null,
    type        VARCHAR2(20),
    project_id  INTEGER,
    create_time DATE,
    content     CLOB,
    operator    VARCHAR2(40),
    state       VARCHAR2(20),
    update_time DATE,
    remark      VARCHAR2(255),
    print_mode  VARCHAR2(20)
)
    tablespace REGIONLIS
    pctfree 10
    initrans 1
    maxtrans 255
    storage
(
    initial 64K
    minextents 1
    maxextents unlimited
);
-- Add comments to the table
comment on table TB_PRINT_TEMPLATE
    is '打印模板';
-- Add comments to the columns
comment on column TB_PRINT_TEMPLATE.type
    is '模板类别';
comment on column TB_PRINT_TEMPLATE.state
    is '''状态：0启用 1停用''';
comment on column TB_PRINT_TEMPLATE.print_mode
    is '''打印方式 01空白纸打印  02套打''';
-- Create/Recreate primary, unique and foreign key constraints
alter table TB_PRINT_TEMPLATE
    add constraint PK_TB_PRINT_TEMPLATE primary key (ID)
        using index
            tablespace REGIONLIS
            pctfree 10
            initrans 2
            maxtrans 255;


-- Create table
create table TB_PRINT_TEMPLATE_CONTENT
(
    id      INTEGER not null,
    temp_id INTEGER,
    content CLOB
)
    tablespace REGIONLIS
    pctfree 10
    initrans 1
    maxtrans 255
    storage
(
    initial 64K
    minextents 1
    maxextents unlimited
);
-- Add comments to the table
comment on table TB_PRINT_TEMPLATE_CONTENT
    is '打印模板-套打内容表打印模板-套打内容表';
-- Create/Recreate primary, unique and foreign key constraints
alter table TB_PRINT_TEMPLATE_CONTENT
    add constraint PK_TB_PRINT_TEMPLATE_CONTENT primary key (ID)
        using index
            tablespace REGIONLIS
            pctfree 10
            initrans 2
            maxtrans 255;


-- ----------------------------
-- 函数 ，代替mysql的find_in_set
-- 例如： select * from rl_sys_org where FIND_IN_SET (101,ancestors) <> 0
-- mysql可接受0或其它number做为where 条件，oracle只接受表达式做为where 条件
-- ----------------------------
create or replace function find_in_set(arg1 in varchar2,arg2 in varchar)
return number is Result number;
begin
select instr(','||arg2||',' , ','||arg1||',') into Result from dual;
return(Result);
end find_in_set;