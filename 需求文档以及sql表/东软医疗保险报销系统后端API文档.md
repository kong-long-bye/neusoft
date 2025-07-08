# 东软医疗保险报销系统后端API文档

## 系统概述
该系统包含三个核心模块：
1. 医疗保险基本信息维护
2. 医院住院医生站医嘱处理  
3. 医保中心报销管理

---

## 一、医疗保险基本信息维护模块

### 1.1 医保药品数据维护

**功能描述：** 根据国家或省市药品目录，维护药品基本信息，供中心报销时录入使用

**子功能与接口：**

#### 1.1.1 药品信息分页查询
- **接口：** `GET /api/drugs/page`
- **参数：** `pageNum, pageSize, drugName`
- **返回：** 分页药品列表

#### 1.1.2 药品信息搜索
- **接口：** `GET /api/drugs/search`
- **参数：** `drugName`
- **返回：** 匹配的药品列表

#### 1.1.3 新增药品信息
- **接口：** `POST /api/drugs`
- **参数：** 药品信息对象（包含药品名称、类型、价格等）
- **返回：** 操作结果

#### 1.1.4 修改药品信息
- **接口：** `PUT /api/drugs/{drugId}`
- **参数：** 药品ID、药品信息对象
- **返回：** 操作结果

#### 1.1.5 删除药品信息（单选/多选）
- **接口：** `DELETE /api/drugs`
- **参数：** 药品ID数组
- **返回：** 操作结果

### 1.2 诊疗项目数据维护

**功能描述：** 根据国家或省市诊疗项目目录，维护诊疗项目基本信息

**子功能与接口：**

#### 1.2.1 诊疗项目分页查询
- **接口：** `GET /api/treatments/page`
- **参数：** `pageNum, pageSize, treatmentName`
- **返回：** 分页诊疗项目列表

#### 1.2.2 诊疗项目搜索
- **接口：** `GET /api/treatments/search`
- **参数：** `treatmentName`
- **返回：** 匹配的诊疗项目列表

#### 1.2.3 新增诊疗项目
- **接口：** `POST /api/treatments`
- **参数：** 诊疗项目信息对象
- **返回：** 操作结果

#### 1.2.4 修改诊疗项目
- **接口：** `PUT /api/treatments/{treatmentId}`
- **参数：** 诊疗项目ID、项目信息对象
- **返回：** 操作结果

#### 1.2.5 删除诊疗项目（单选/多选）
- **接口：** `DELETE /api/treatments`
- **参数：** 诊疗项目ID数组
- **返回：** 操作结果

### 1.3 医疗服务设施数据维护

**功能描述：** 根据国家或省市医疗服务设施目录，维护服务设施基本信息

**子功能与接口：**

#### 1.3.1 医疗服务设施分页查询
- **接口：** `GET /api/medical-facilities/page`
- **参数：** `pageNum, pageSize, facilityName`
- **返回：** 分页医疗服务设施列表

#### 1.3.2 医疗服务设施搜索
- **接口：** `GET /api/medical-facilities/search`
- **参数：** `facilityName`
- **返回：** 匹配的医疗服务设施列表

#### 1.3.3 新增医疗服务设施
- **接口：** `POST /api/medical-facilities`
- **参数：** 医疗服务设施信息对象
- **返回：** 操作结果

#### 1.3.4 修改医疗服务设施
- **接口：** `PUT /api/medical-facilities/{facilityId}`
- **参数：** 设施ID、设施信息对象
- **返回：** 操作结果

#### 1.3.5 删除医疗服务设施（单选/多选）
- **接口：** `DELETE /api/medical-facilities`
- **参数：** 设施ID数组
- **返回：** 操作结果

### 1.4 药品报销比例维护

**功能描述：** 对甲乙丙三类药品报销比例进行设置

**子功能与接口：**

#### 1.4.1 获取药品报销比例列表
- **接口：** `GET /api/drug-reimbursement-ratios`
- **返回：** 药品报销比例列表

#### 1.4.2 修改药品报销比例
- **接口：** `PUT /api/drug-reimbursement-ratios/{ratioId}`
- **参数：** 比例ID、报销比例信息
- **返回：** 操作结果

#### 1.4.3 新增药品类型
- **接口：** `POST /api/drug-reimbursement-ratios`
- **参数：** 药品类型、报销比例信息
- **返回：** 操作结果

#### 1.4.4 删除药品类型
- **接口：** `DELETE /api/drug-reimbursement-ratios/{ratioId}`
- **参数：** 比例ID
- **返回：** 操作结果

### 1.5 三级医院报销比例维护

**功能描述：** 对三级医院报销比例进行设置

**子功能与接口：**

#### 1.5.1 获取三级医院报销比例
- **接口：** `GET /api/level3-hospital-ratios`
- **参数：** `employeeType`（在职/退休）
- **返回：** 报销比例列表

#### 1.5.2 修改报销比例
- **接口：** `PUT /api/level3-hospital-ratios/{ratioId}`
- **参数：** 比例ID、报销比例信息
- **返回：** 操作结果

#### 1.5.3 新增费用区间
- **接口：** `POST /api/level3-hospital-ratios`
- **参数：** 费用区间、报销比例信息
- **返回：** 操作结果

#### 1.5.4 删除费用区间
- **接口：** `DELETE /api/level3-hospital-ratios/{ratioId}`
- **参数：** 比例ID
- **返回：** 操作结果

### 1.6 二级医院报销比例维护

**功能描述：** 对二级医院报销比例进行设置

**子功能与接口：**

#### 1.6.1 获取二级医院报销比例
- **接口：** `GET /api/level2-hospital-ratios`
- **参数：** `employeeType`（在职/退休）
- **返回：** 报销比例列表

#### 1.6.2 修改报销比例
- **接口：** `PUT /api/level2-hospital-ratios/{ratioId}`
- **参数：** 比例ID、报销比例信息
- **返回：** 操作结果

#### 1.6.3 新增费用区间
- **接口：** `POST /api/level2-hospital-ratios`
- **参数：** 费用区间、报销比例信息
- **返回：** 操作结果

#### 1.6.4 删除费用区间
- **接口：** `DELETE /api/level2-hospital-ratios/{ratioId}`
- **参数：** 比例ID
- **返回：** 操作结果

### 1.7 一级医院报销比例维护

**功能描述：** 对一级医院报销比例进行设置

**子功能与接口：**

#### 1.7.1 获取一级医院报销比例
- **接口：** `GET /api/level1-hospital-ratios`
- **参数：** `employeeType`（在职/退休）
- **返回：** 报销比例列表

#### 1.7.2 修改报销比例
- **接口：** `PUT /api/level1-hospital-ratios/{ratioId}`
- **参数：** 比例ID、报销比例信息
- **返回：** 操作结果

#### 1.7.3 新增费用区间
- **接口：** `POST /api/level1-hospital-ratios`
- **参数：** 费用区间、报销比例信息
- **返回：** 操作结果

#### 1.7.4 删除费用区间
- **接口：** `DELETE /api/level1-hospital-ratios/{ratioId}`
- **参数：** 比例ID
- **返回：** 操作结果

---

## 二、医院住院医生站医嘱处理模块

### 2.1 患者疾病诊断

**功能描述：** 医生录入患者疾病信息

**子功能与接口：**

#### 2.1.1 疾病信息分页查询
- **接口：** `GET /api/patient-diagnoses/page`
- **参数：** `pageNum, pageSize, diseaseName, patientId`
- **返回：** 分页疾病诊断列表

#### 2.1.2 疾病信息搜索
- **接口：** `GET /api/patient-diagnoses/search`
- **参数：** `diseaseName`
- **返回：** 匹配的疾病列表

#### 2.1.3 新增患者疾病诊断
- **接口：** `POST /api/patient-diagnoses`
- **参数：** 患者ID、疾病诊断信息
- **返回：** 操作结果

### 2.2 患者药品处方医嘱

**功能描述：** 医生录入患者使用药品信息

**子功能与接口：**

#### 2.2.1 患者药品处方分页查询
- **接口：** `GET /api/patient-prescriptions/page`
- **参数：** `pageNum, pageSize, drugName, patientId`
- **返回：** 分页药品处方列表

#### 2.2.2 药品信息搜索
- **接口：** `GET /api/patient-prescriptions/search`
- **参数：** `drugName`
- **返回：** 匹配的药品列表

#### 2.2.3 新增患者药品处方
- **接口：** `POST /api/patient-prescriptions`
- **参数：** 患者ID、药品处方信息（药品ID、剂量、用法等）
- **返回：** 操作结果

### 2.3 患者诊疗项目医嘱

**功能描述：** 医生录入患者使用诊疗项目信息

**子功能与接口：**

#### 2.3.1 患者诊疗项目分页查询
- **接口：** `GET /api/patient-treatments/page`
- **参数：** `pageNum, pageSize, treatmentName, patientId`
- **返回：** 分页诊疗项目列表

#### 2.3.2 诊疗项目搜索
- **接口：** `GET /api/patient-treatments/search`
- **参数：** `treatmentName`
- **返回：** 匹配的诊疗项目列表

#### 2.3.3 新增患者诊疗项目
- **接口：** `POST /api/patient-treatments`
- **参数：** 患者ID、诊疗项目信息
- **返回：** 操作结果

### 2.4 患者医疗服务医嘱

**功能描述：** 医生录入患者使用医疗服务信息

**子功能与接口：**

#### 2.4.1 患者医疗服务分页查询
- **接口：** `GET /api/patient-medical-services/page`
- **参数：** `pageNum, pageSize, serviceName, patientId`
- **返回：** 分页医疗服务列表

#### 2.4.2 医疗服务搜索
- **接口：** `GET /api/patient-medical-services/search`
- **参数：** `serviceName`
- **返回：** 匹配的医疗服务列表

#### 2.4.3 新增患者医疗服务
- **接口：** `POST /api/patient-medical-services`
- **参数：** 患者ID、医疗服务信息
- **返回：** 操作结果

---

## 三、医保中心报销管理模块

### 3.1 参保人员保险信息维护

**功能描述：** 维护参保人员基本信息，设置是否医保、在职/退休状态

**子功能与接口：**

#### 3.1.1 参保人员分页查询
- **接口：** `GET /api/insured-persons/page`
- **参数：** `pageNum, pageSize, personName`
- **返回：** 分页参保人员列表

#### 3.1.2 参保人员搜索
- **接口：** `GET /api/insured-persons/search`
- **参数：** `personName`
- **返回：** 匹配的参保人员列表

#### 3.1.3 新增参保人员
- **接口：** `POST /api/insured-persons`
- **参数：** 参保人员信息（姓名、身份证、医保状态、就业状态等）
- **返回：** 操作结果

#### 3.1.4 修改参保人员信息
- **接口：** `PUT /api/insured-persons/{personId}`
- **参数：** 人员ID、参保人员信息
- **返回：** 操作结果

### 3.2 参保人员费用查询

**功能描述：** 查询参保人员使用药品、诊疗项目、医疗服务的费用详情

**子功能与接口：**

#### 3.2.1 参保人员分页查询
- **接口：** `GET /api/insured-persons/page`
- **参数：** `pageNum, pageSize, personName`
- **返回：** 分页参保人员列表

#### 3.2.2 参保人员搜索
- **接口：** `GET /api/insured-persons/search`
- **参数：** `personName`
- **返回：** 匹配的参保人员列表

#### 3.2.3 参保人员费用详情查询
- **接口：** `GET /api/insured-persons/{personId}/expenses`
- **参数：** 人员ID、费用类型（药品/诊疗/医疗服务）
- **返回：** 分类费用详情

### 3.3 参保人员保险费用报销详情报表

**功能描述：** 生成参保人员费用报销详情报表，包含图表展示

**子功能与接口：**

#### 3.3.1 参保人员分页查询
- **接口：** `GET /api/insured-persons/page`
- **参数：** `pageNum, pageSize, personName`
- **返回：** 分页参保人员列表

#### 3.3.2 参保人员搜索
- **接口：** `GET /api/insured-persons/search`
- **参数：** `personName`
- **返回：** 匹配的参保人员列表

#### 3.3.3 费用报销详情报表
- **接口：** `GET /api/insured-persons/{personId}/reimbursement-report`
- **参数：** 人员ID
- **返回：** 费用报销详情数据（包含甲乙丙类药品占比、各类费用占比等图表数据）

### 3.4 参保人员保险费用报销

**功能描述：** 按照系统设置的医疗保险报销比例进行计算和报销操作

**子功能与接口：**

#### 3.4.1 参保人员分页查询
- **接口：** `GET /api/insured-persons/page`
- **参数：** `pageNum, pageSize, personName`
- **返回：** 分页参保人员列表

#### 3.4.2 参保人员搜索
- **接口：** `GET /api/insured-persons/search`
- **参数：** `personName`
- **返回：** 匹配的参保人员列表

#### 3.4.3 费用报销计算
- **接口：** `GET /api/insured-persons/{personId}/reimbursement-calculation`
- **参数：** 人员ID、医院等级
- **返回：** 报销金额计算结果

#### 3.4.4 执行费用报销
- **接口：** `POST /api/insured-persons/{personId}/reimbursement`
- **参数：** 人员ID、报销信息
- **返回：** 报销操作结果

---

## 技术规范

### 统一响应格式
```json
{
  "code": 200,
  "message": "success", 
  "data": {}
}
```

### 分页响应格式
```json
{
  "code": 200,
  "message": "success",
  "data": {
    "total": 100,
    "pageNum": 1,
    "pageSize": 10,
    "pages": 10,
    "list": []
  }
}
```

### 技术栈要求
- **后端框架：** SpringBoot + SpringMVC + Spring
- **持久层：** MyBatis
- **数据库：** MySQL
- **构建工具：** Maven
- **开发工具：** IDEA
- **前端框架：** Vue3 (TypeScript)
- **API文档：** Swagger

### 开发环境要求
- **JDK版本：** JDK 1.8

### 安全要求
- 实现用户访问控制，包含登录验证、权限管理
- 添加图片验证码防止暴力破解
- 对用户输入进行安全验证
- 实现系统操作审计功能