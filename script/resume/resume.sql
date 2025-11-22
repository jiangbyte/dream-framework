-- 简历基本信息表
DROP TABLE IF EXISTS resume_profile;
CREATE TABLE resume_profile
(
    -- 基础字段
    id                   VARCHAR(32) PRIMARY KEY, -- 主键ID
    -- 个人信息
    name                 VARCHAR(100) NOT NULL,   -- 姓名
    phone                VARCHAR(20),             -- 电话
    email                VARCHAR(100),            -- 邮箱
    birth_date           VARCHAR(50),             -- 出生年月
    current_address      VARCHAR(200),            -- 现住址
    major                VARCHAR(100),            -- 专业
    education_background VARCHAR(200),            -- 教育背景
    photo_url            VARCHAR(500),            -- 照片URL
    -- 显示控制
    show_phone           BOOLEAN DEFAULT TRUE,    -- 是否显示电话
    show_email           BOOLEAN DEFAULT TRUE,    -- 是否显示邮箱
    show_birth           BOOLEAN DEFAULT TRUE,    -- 是否显示出生年月
    show_address         BOOLEAN DEFAULT TRUE,    -- 是否显示住址
    show_photo           BOOLEAN DEFAULT TRUE,    -- 是否显示照片
    display_order        INT DEFAULT 0,           -- 显示顺序
    -- 基础字段
    is_deleted           BOOLEAN   DEFAULT FALSE, -- 是否删除
    deleted_at           TIMESTAMP,               -- 软删除时间
    delete_user          VARCHAR(32),             -- 删除操作人
    created_at           TIMESTAMP DEFAULT NOW(), -- 创建时间
    create_user          VARCHAR(32),             -- 创建人
    updated_at           TIMESTAMP DEFAULT NOW(), -- 更新时间
    update_user          VARCHAR(32)              -- 更新人
);

COMMENT ON TABLE resume_profile IS '简历基本信息表';
COMMENT ON COLUMN resume_profile.id IS '主键ID';
COMMENT ON COLUMN resume_profile.name IS '姓名';
COMMENT ON COLUMN resume_profile.phone IS '电话';
COMMENT ON COLUMN resume_profile.email IS '邮箱';
COMMENT ON COLUMN resume_profile.birth_date IS '出生年月';
COMMENT ON COLUMN resume_profile.current_address IS '现住址';
COMMENT ON COLUMN resume_profile.major IS '专业';
COMMENT ON COLUMN resume_profile.education_background IS '教育背景';
COMMENT ON COLUMN resume_profile.photo_url IS '照片URL';
COMMENT ON COLUMN resume_profile.show_phone IS '是否显示电话';
COMMENT ON COLUMN resume_profile.show_email IS '是否显示邮箱';
COMMENT ON COLUMN resume_profile.show_birth IS '是否显示出生年月';
COMMENT ON COLUMN resume_profile.show_address IS '是否显示住址';
COMMENT ON COLUMN resume_profile.show_photo IS '是否显示照片';
COMMENT ON COLUMN resume_profile.display_order IS '显示顺序';
COMMENT ON COLUMN resume_profile.is_deleted IS '是否删除';
COMMENT ON COLUMN resume_profile.deleted_at IS '软删除时间';
COMMENT ON COLUMN resume_profile.delete_user IS '删除操作人';
COMMENT ON COLUMN resume_profile.created_at IS '创建时间';
COMMENT ON COLUMN resume_profile.create_user IS '创建人';
COMMENT ON COLUMN resume_profile.updated_at IS '更新时间';
COMMENT ON COLUMN resume_profile.update_user IS '更新人';

-- 技术技能表
DROP TABLE IF EXISTS resume_skill;
CREATE TABLE resume_skill
(
    id            VARCHAR(32) PRIMARY KEY,
    profile_id    VARCHAR(32) NOT NULL,           -- 关联简历ID
    category      VARCHAR(100) NOT NULL,          -- 技能分类（编程语言、后端开发等）
    skill_name    VARCHAR(200) NOT NULL,          -- 技能名称
    proficiency   VARCHAR(50),                    -- 熟练程度（熟练掌握、熟悉、了解等）
    description   TEXT,                           -- 详细描述
    display_order INT DEFAULT 0,                  -- 显示顺序
    is_visible    BOOLEAN DEFAULT TRUE,           -- 是否显示
    is_deleted    BOOLEAN   DEFAULT FALSE,
    deleted_at    TIMESTAMP,
    delete_user   VARCHAR(32),
    created_at    TIMESTAMP DEFAULT NOW(),
    create_user   VARCHAR(32),
    updated_at    TIMESTAMP DEFAULT NOW(),
    update_user   VARCHAR(32),
    FOREIGN KEY (profile_id) REFERENCES resume_profile (id)
);

COMMENT ON TABLE resume_skill IS '技术技能表';
COMMENT ON COLUMN resume_skill.id IS '主键ID';
COMMENT ON COLUMN resume_skill.profile_id IS '关联简历ID';
COMMENT ON COLUMN resume_skill.category IS '技能分类（编程语言、后端开发等）';
COMMENT ON COLUMN resume_skill.skill_name IS '技能名称';
COMMENT ON COLUMN resume_skill.proficiency IS '熟练程度（熟练掌握、熟悉、了解等）';
COMMENT ON COLUMN resume_skill.description IS '详细描述';
COMMENT ON COLUMN resume_skill.display_order IS '显示顺序';
COMMENT ON COLUMN resume_skill.is_visible IS '是否显示';
COMMENT ON COLUMN resume_skill.is_deleted IS '是否删除';
COMMENT ON COLUMN resume_skill.deleted_at IS '软删除时间';
COMMENT ON COLUMN resume_skill.delete_user IS '删除操作人';
COMMENT ON COLUMN resume_skill.created_at IS '创建时间';
COMMENT ON COLUMN resume_skill.create_user IS '创建人';
COMMENT ON COLUMN resume_skill.updated_at IS '更新时间';
COMMENT ON COLUMN resume_skill.update_user IS '更新人';

-- 经历表（统一存储实习、项目、校园经历）
DROP TABLE IF EXISTS resume_experience;
CREATE TABLE resume_experience
(
    id            VARCHAR(32) PRIMARY KEY,
    profile_id    VARCHAR(32) NOT NULL,           -- 关联简历ID
    type          VARCHAR(50) NOT NULL,           -- 经历类型（INTERNSHIP-实习, PROJECT-项目, CAMPUS-校园）
    title         VARCHAR(200) NOT NULL,          -- 经历标题
    company       VARCHAR(200),                   -- 公司/组织
    position      VARCHAR(100),                   -- 职位/角色
    start_date    VARCHAR(50),                    -- 开始时间
    end_date      VARCHAR(50),                    -- 结束时间
    description   TEXT,                           -- 详细描述（支持Markdown格式）
    project_link  VARCHAR(500),                   -- 项目链接
    display_order INT DEFAULT 0,                  -- 显示顺序
    is_visible    BOOLEAN DEFAULT TRUE,           -- 是否显示
    is_deleted    BOOLEAN   DEFAULT FALSE,
    deleted_at    TIMESTAMP,
    delete_user   VARCHAR(32),
    created_at    TIMESTAMP DEFAULT NOW(),
    create_user   VARCHAR(32),
    updated_at    TIMESTAMP DEFAULT NOW(),
    update_user   VARCHAR(32),
    FOREIGN KEY (profile_id) REFERENCES resume_profile (id)
);

COMMENT ON TABLE resume_experience IS '经历表（统一存储实习、项目、校园经历）';
COMMENT ON COLUMN resume_experience.id IS '主键ID';
COMMENT ON COLUMN resume_experience.profile_id IS '关联简历ID';
COMMENT ON COLUMN resume_experience.type IS '经历类型（INTERNSHIP-实习, PROJECT-项目, CAMPUS-校园）';
COMMENT ON COLUMN resume_experience.title IS '经历标题';
COMMENT ON COLUMN resume_experience.company IS '公司/组织';
COMMENT ON COLUMN resume_experience.position IS '职位/角色';
COMMENT ON COLUMN resume_experience.start_date IS '开始时间';
COMMENT ON COLUMN resume_experience.end_date IS '结束时间';
COMMENT ON COLUMN resume_experience.description IS '详细描述（支持Markdown格式）';
COMMENT ON COLUMN resume_experience.project_link IS '项目链接';
COMMENT ON COLUMN resume_experience.display_order IS '显示顺序';
COMMENT ON COLUMN resume_experience.is_visible IS '是否显示';
COMMENT ON COLUMN resume_experience.is_deleted IS '是否删除';
COMMENT ON COLUMN resume_experience.deleted_at IS '软删除时间';
COMMENT ON COLUMN resume_experience.delete_user IS '删除操作人';
COMMENT ON COLUMN resume_experience.created_at IS '创建时间';
COMMENT ON COLUMN resume_experience.create_user IS '创建人';
COMMENT ON COLUMN resume_experience.updated_at IS '更新时间';
COMMENT ON COLUMN resume_experience.update_user IS '更新人';

-- 获奖记录表
DROP TABLE IF EXISTS resume_award;
CREATE TABLE resume_award
(
    id            VARCHAR(32) PRIMARY KEY,
    profile_id    VARCHAR(32) NOT NULL,           -- 关联简历ID
    award_name    VARCHAR(200) NOT NULL,          -- 奖项名称
    level         VARCHAR(50),                    -- 奖项级别（国家级、省级、校级等）
    ranking       VARCHAR(50),                    -- 获奖等级（一等奖、二等奖等）
    award_date    VARCHAR(50),                    -- 获奖时间
    organization  VARCHAR(200),                   -- 颁发机构
    description   TEXT,                           -- 描述
    display_order INT DEFAULT 0,                  -- 显示顺序
    is_visible    BOOLEAN DEFAULT TRUE,           -- 是否显示
    is_deleted    BOOLEAN   DEFAULT FALSE,
    deleted_at    TIMESTAMP,
    delete_user   VARCHAR(32),
    created_at    TIMESTAMP DEFAULT NOW(),
    create_user   VARCHAR(32),
    updated_at    TIMESTAMP DEFAULT NOW(),
    update_user   VARCHAR(32),
    FOREIGN KEY (profile_id) REFERENCES resume_profile (id)
);

COMMENT ON TABLE resume_award IS '获奖记录表';
COMMENT ON COLUMN resume_award.id IS '主键ID';
COMMENT ON COLUMN resume_award.profile_id IS '关联简历ID';
COMMENT ON COLUMN resume_award.award_name IS '奖项名称';
COMMENT ON COLUMN resume_award.level IS '奖项级别（国家级、省级、校级等）';
COMMENT ON COLUMN resume_award.ranking IS '获奖等级（一等奖、二等奖等）';
COMMENT ON COLUMN resume_award.award_date IS '获奖时间';
COMMENT ON COLUMN resume_award.organization IS '颁发机构';
COMMENT ON COLUMN resume_award.description IS '描述';
COMMENT ON COLUMN resume_award.display_order IS '显示顺序';
COMMENT ON COLUMN resume_award.is_visible IS '是否显示';
COMMENT ON COLUMN resume_award.is_deleted IS '是否删除';
COMMENT ON COLUMN resume_award.deleted_at IS '软删除时间';
COMMENT ON COLUMN resume_award.delete_user IS '删除操作人';
COMMENT ON COLUMN resume_award.created_at IS '创建时间';
COMMENT ON COLUMN resume_award.create_user IS '创建人';
COMMENT ON COLUMN resume_award.updated_at IS '更新时间';
COMMENT ON COLUMN resume_award.update_user IS '更新人';

-- 简历配置表（用于控制整体显示）
DROP TABLE IF EXISTS resume_config;
CREATE TABLE resume_config
(
    id                   VARCHAR(32) PRIMARY KEY,
    profile_id           VARCHAR(32) NOT NULL,    -- 关联简历ID
    show_skills          BOOLEAN DEFAULT TRUE,    -- 是否显示技术技能模块
    show_experiences     BOOLEAN DEFAULT TRUE,    -- 是否显示经历模块
    show_internships     BOOLEAN DEFAULT TRUE,    -- 是否显示实习经历
    show_projects        BOOLEAN DEFAULT TRUE,    -- 是否显示项目经历
    show_campus          BOOLEAN DEFAULT TRUE,    -- 是否显示校园经历
    show_awards          BOOLEAN DEFAULT TRUE,    -- 是否显示获奖记录
    language             VARCHAR(10) DEFAULT 'zh', -- 语言（zh-中文, en-英文）
    theme                VARCHAR(50) DEFAULT 'default', -- 主题样式
    is_deleted           BOOLEAN   DEFAULT FALSE,
    deleted_at           TIMESTAMP,
    delete_user          VARCHAR(32),
    created_at           TIMESTAMP DEFAULT NOW(),
    create_user          VARCHAR(32),
    updated_at           TIMESTAMP DEFAULT NOW(),
    update_user          VARCHAR(32),
    FOREIGN KEY (profile_id) REFERENCES resume_profile (id)
);

COMMENT ON TABLE resume_config IS '简历配置表（用于控制整体显示）';
COMMENT ON COLUMN resume_config.id IS '主键ID';
COMMENT ON COLUMN resume_config.profile_id IS '关联简历ID';
COMMENT ON COLUMN resume_config.show_skills IS '是否显示技术技能模块';
COMMENT ON COLUMN resume_config.show_experiences IS '是否显示经历模块';
COMMENT ON COLUMN resume_config.show_internships IS '是否显示实习经历';
COMMENT ON COLUMN resume_config.show_projects IS '是否显示项目经历';
COMMENT ON COLUMN resume_config.show_campus IS '是否显示校园经历';
COMMENT ON COLUMN resume_config.show_awards IS '是否显示获奖记录';
COMMENT ON COLUMN resume_config.language IS '语言（zh-中文, en-英文）';
COMMENT ON COLUMN resume_config.theme IS '主题样式';
COMMENT ON COLUMN resume_config.is_deleted IS '是否删除';
COMMENT ON COLUMN resume_config.deleted_at IS '软删除时间';
COMMENT ON COLUMN resume_config.delete_user IS '删除操作人';
COMMENT ON COLUMN resume_config.created_at IS '创建时间';
COMMENT ON COLUMN resume_config.create_user IS '创建人';
COMMENT ON COLUMN resume_config.updated_at IS '更新时间';
COMMENT ON COLUMN resume_config.update_user IS '更新人';

-- 创建索引以提高查询性能
CREATE INDEX idx_resume_skill_profile_id ON resume_skill (profile_id);
CREATE INDEX idx_resume_skill_visible ON resume_skill (is_visible);
CREATE INDEX idx_resume_experience_profile_id ON resume_experience (profile_id);
CREATE INDEX idx_resume_experience_type ON resume_experience (type);
CREATE INDEX idx_resume_experience_visible ON resume_experience (is_visible);
CREATE INDEX idx_resume_award_profile_id ON resume_award (profile_id);
CREATE INDEX idx_resume_award_visible ON resume_award (is_visible);
CREATE INDEX idx_resume_config_profile_id ON resume_config (profile_id);