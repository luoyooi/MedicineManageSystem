// 顶部
const Header = {
    props: ['emp_name'],
    template: `
        <div id="header_sub">
            <span style="font-size: 18px; margin-left: 20px">医药销售管理系统</span>
            <div>
                <el-dropdown @command="managerCommand">
                    <i class="el-icon-setting" style="color: #fff; width: 25px;"></i>
                    <el-dropdown-menu slot="dropdown">
                        <el-dropdown-item command="look">查看</el-dropdown-item>
                        <el-dropdown-item command="quit">退出</el-dropdown-item>
                    </el-dropdown-menu>
                </el-dropdown>
                <span style="margin-right: 20px">{{emp_name}}</span>
            </div>
        </div>
    `,
    methods: {
        managerCommand(cmd){
            if (cmd === 'look')
            {
                // 查看相关操作
                alert("查看信息");
            }
            else if (cmd === 'quit')
            {
                // 清空登录信息
                window.sessionStorage.setItem("manager_info", null);
                // 跳转到登录页面
                window.location.assign("login.html");
            }
        }
    }
}

// 侧边栏
const Side = {
    props: ['is_manager'],
    template: `
        <el-menu>
            <el-submenu index="1" v-if="is_manager">
                <template slot="title"><i class="el-icon-user-solid"></i>员工管理</template>
                <el-menu-item index="1-1" class="choice-item" @click='$emit("show", "/showemps")'>员工信息</el-menu-item>
                <el-menu-item index="1-2" class="choice-item" @click='$emit("show", "/addemp")'>新增员工</el-menu-item>
            </el-submenu>
            <el-submenu index="2">
                <template slot="title"><i class="el-icon-picture-outline-round"></i>药品管理</template>
                <el-menu-item index="2-1" class="choice-item" @click='$emit("show", "/showemeds")'>药品数据</el-menu-item>
                <el-menu-item index="2-2" class="choice-item" @click='$emit("show", "/showeaddmed")'>新增药品</el-menu-item>
                <el-menu-item index="2-3" class="choice-item" @click='$emit("show", "/showputsto")'>新增库存</el-menu-item>
                <el-menu-item index="2-4" class="choice-item" @click='$emit("show", "/showmedtype")'>药品类别</el-menu-item>
            </el-submenu>
            <el-submenu index="3">
                <template slot="title"><i class="el-icon-upload"></i>数据分析</template>
                <el-menu-item index="3-1" class="choice-item" @click='$emit("show", "/showestorage")'>入库记录</el-menu-item>
                <el-menu-item index="3-2" class="choice-item" @click='$emit("show", "/showorders")'>订单统计</el-menu-item>
            </el-submenu>
            <el-submenu index="4">
                <template slot="title"><i class="el-icon-s-grid"></i>扩展功能</template>
                <el-menu-item index="4-1" class="choice-item" @click='$emit("show", "/addinfo")'>批量导入</el-menu-item>
            </el-submenu>
        </el-menu>
    `,
}

// 显示员工信息
const Emps = {
    template: `
        <div>
            <el-table :data="tableData" stripe style="width: 100%;padding-bottom: 60px;">
                <el-table-column prop="empName" label="姓名" width="120"></el-table-column>
                <el-table-column label="性别" width="80">
                    <template slot-scope="scope">
                        <p>{{scope.row.gender | genderFilter}}</p>
                    </template>
                </el-table-column>
                <el-table-column prop="age" label="年龄" width="80"></el-table-column>
                <el-table-column prop="phone" label="电话" width="170"></el-table-column>
                <el-table-column prop="email" label="邮箱" width="170"></el-table-column>
                <el-table-column prop="loginName" label="账号" width="150"></el-table-column>
                <el-table-column prop="loginPassword" label="密码" width="150"></el-table-column>
                <el-table-column label="管理员" width="100">
                    <template slot-scope="scope">
                        <p>{{scope.row.manager | managerFilter}}</p>
                    </template>
                </el-table-column>
                <el-table-column label="操作">
                 <!--编辑员工-->
                  <template slot-scope="scope">
                    <el-button size="mini" @click="handleEdit(scope.row)">编辑</el-button>
                    <!--编辑对话框-->
                    <el-dialog title="编辑员工信息" :visible.sync="dialogVisible">
                        <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
                            <el-form-item label="姓名" prop="empName">
                                <el-input v-model="ruleForm.empName"></el-input>
                            </el-form-item>
                            <el-form-item label="性别" prop="gender">
                                <el-radio-group v-model="ruleForm.gender">
                                    <el-radio :label="true">男</el-radio>
                                    <el-radio :label="false">女</el-radio>
                                </el-radio-group>
                            </el-form-item>
                            <el-form-item label="年龄" prop="age">
                                <el-input v-model.number="ruleForm.age"></el-input>
                            </el-form-item>
                            <el-form-item label="电话" prop="phone">
                                <el-input v-model="ruleForm.phone"></el-input>
                            </el-form-item>
                            <el-form-item label="邮箱" prop="email">
                                <el-input v-model="ruleForm.email"></el-input>
                            </el-form-item>
                            <el-form-item label="账号" prop="loginName">
                                <el-input v-model="ruleForm.loginName" disabled></el-input>
                            </el-form-item>
                            <el-form-item label="密码" prop="loginPassword">
                                <el-input v-model="ruleForm.loginPassword"></el-input>
                            </el-form-item>
                            <el-form-item label="管理员" prop="manager">
                                <el-radio-group v-model="ruleForm.manager">
                                    <el-radio :label="true">是</el-radio>
                                    <el-radio :label="false">否</el-radio>
                                </el-radio-group>
                            </el-form-item>
                        </el-form>
                        <span slot="footer" class="dialog-footer">
                            <el-button @click="dialogVisible = false">取 消</el-button>
                            <el-button type="primary" @click="updateEmp(scope.row)">确 定</el-button>
                        </span>
                    </el-dialog>
                    <!--删除员工-->
                    <el-button size="mini" type="danger" @click="handleDelete(scope.row)">删除</el-button>
                  </template>
                </el-table-column>
            </el-table>
        </div>
    `,
    data(){
        return {
            tableData: [],
            dialogVisible: false,
            ruleForm: {},
            rules: {
                empName: [
                    { required: true, message: '请输入员工姓名', trigger: 'blur'}
                ],
                age: [
                    { required: true, message: '年龄不能为空'},
                    { type: 'number', message: '年龄必须为数字值'}
                ],
                loginName: [
                    { required: true, message: '请输入登录账户', trigger: 'blur'},
                    { min: 3, max: 10, message: '长度在 3 到 10 个字符' }
                ],
                loginPassword: [
                    { required: true, message: '请输入登录密码', trigger: 'blur'},
                    { min: 6, max: 16, message: '长度在 6 到 16 个字符' }
                ],
            }
        }
    },
    filters:{
      genderFilter(val){
          return val ? '男' : '女';
      },
        managerFilter(val){
            return val ? '是' : '否';
      },
    },
    methods:{
        getEmp(){
            // 获取所有可用员工信息
            axios.get('emp')
                .then(res=>{
                    this.tableData = res.data;
                });
        },
        updateEmp(row){
            // 发送更新请求
            axios.put('emp', this.ruleForm)
                .then(res=>{
                    // 若更新失败，恢复更新之前的状态
                    if (!res.data)
                    {
                        this.$message({
                            type: 'success',
                            message: '修改失败!'
                        });
                    }
                    else
                    {

                        // 修改成功
                        this.$message({
                            type: 'success',
                            message: '修改成功!'
                        });
                    }

                    // 刷新数据
                    this.getEmp();

                    // 关闭弹窗
                    this.dialogVisible = false;
                })
        },
        handleEdit(row) {
            this.dialogVisible = true;
            this.ruleForm = row;
        },
        handleDelete(row) {
            this.$confirm('此操作将永久删除该记录, 是否继续?', '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning'
            }).then(() => {
                axios.delete('emp?loginName=' + row.loginName).then(res=>{
                    if (res){
                        this.$message({
                            type: 'success',
                            message: '删除成功!'
                        });
                    }
                    else
                    {
                        this.$message({
                            type: 'error',
                            message: '删除失败'
                        });
                    }

                    // 刷新数据
                    this.getEmp();
                })
            }).catch(() => {
                this.$message({
                    type: 'info',
                    message: '已取消删除'
                });
            });
        }
    },
    mounted() {
        this.getEmp();
    }
}

// 添加员工界面
const AddEmp = {
    template: `
        <el-form :model="ruleForm" :rules="rules" ref="ruleForm" 
        label-width="70px" 
        style="width: 600px; padding: 10px 30px; box-sizing: border-box;
        box-shadow: 0 2px 6px rgba(0, 0, 0, .12), 0 0 6px rgba(0, 0, 0, .04); 
        background-color:#d9e8f3; border-radius: 4px" class="demo-ruleForm">
            <p style="text-align: center; font-size: 18px;line-height: 36px;">员工信息添加</p>
            <el-form-item label="姓名" prop="empName">
                <el-input v-model="ruleForm.empName"></el-input>
            </el-form-item>
            <el-form-item label="性别" prop="gender">
                <el-radio-group v-model="ruleForm.gender">
                    <el-radio :label="true">男</el-radio>
                    <el-radio :label="false">女</el-radio>
                </el-radio-group>
            </el-form-item>
            <el-form-item label="年龄" prop="age">
                <el-input v-model.number="ruleForm.age"></el-input>
            </el-form-item>
            <el-form-item label="电话" prop="phone">
                <el-input v-model="ruleForm.phone"></el-input>
            </el-form-item>
            <el-form-item label="邮箱" prop="email">
                <el-input v-model="ruleForm.email"></el-input>
            </el-form-item>
            <el-form-item label="账号" prop="loginName">
                <el-input v-model="ruleForm.loginName"></el-input>
            </el-form-item>
            <el-form-item label="密码" prop="loginPassword">
                <el-input v-model="ruleForm.loginPassword"></el-input>
            </el-form-item>
            <el-form-item label="管理员" prop="manager">
                <el-radio-group v-model="ruleForm.manager">
                    <el-radio :label="true">是</el-radio>
                    <el-radio :label="false">否</el-radio>
                </el-radio-group>
            </el-form-item>
            <el-form-item>
                <el-button type="primary" @click="submitForm('ruleForm')">提交</el-button>
                <el-button @click="resetForm('ruleForm')">重置</el-button>
             </el-form-item>
        </el-form>
    `,
    data(){
        return {
            ruleForm: {},
            rules: {
                empName: [
                    { required: true, message: '请输入员工姓名', trigger: 'blur'}
                ],
                gender: [
                    { required: true, message: '性别不能为空'}
                ],
                age: [
                    { required: true, message: '年龄不能为空'},
                    { type: 'number', message: '年龄必须为数字值'}
                ],
                loginName: [
                    { required: true, message: '请输入登录账号', trigger: 'blur'},
                    { min: 3, max: 10, message: '长度在 3 到 10 个字符' }
                ],
                loginPassword: [
                    { required: true, message: '请输入登录密码', trigger: 'blur'},
                    { min: 6, max: 16, message: '长度在 6 到 16 个字符' }
                ],
                manager: [
                    { required: true, message: '管理员属性不能为空'}
                ],
            }
        }
    },
    methods: {
        submitForm(formName) {
            this.$refs[formName].validate(async (valid) => {
                if (valid) {
                    // 验证是否存在该账号
                    let isExist = await axios.get('login?login_name=' + this.ruleForm.loginName);

                    // 若不存在，提交数据
                    if (!isExist.data)
                    {
                        this.ruleForm.use = true;
                        let res = await axios.post('emp', this.ruleForm);

                        // 若返回true，说明提交成功
                        if (res.data)
                        {
                            this.$message({
                                type: 'success',
                                message: '添加成功'
                            });
                        }
                        else
                        {
                            this.$message({
                                type: 'error',
                                message: '添加失败'
                            });
                        }
                    }
                    else
                    {
                        this.$message({
                            type: 'error',
                            message: '登录账号已存在'
                        });
                    }

                } else {
                    this.$message({
                        type: 'error',
                        message: '填写信息有误'
                    });
                    return false;
                }
            });
        },
        resetForm(formName) {
            this.$refs[formName].resetFields();
        }
    }
}

// 显示药品信息
const Meds = {
    template: `
        <div>
            <el-select v-model="medType" filterable placeholder="请选择">
                <el-option
                  v-for="item in options"
                  :key="item.value"
                  :label="item.label"
                  :value="item.value">
                </el-option>
            </el-select>
            <el-button type="primary" icon="el-icon-search" @click="search" circle></el-button>
            <span style="float: right;margin-right: 20px">
                <i style="cursor: pointer;" class="el-icon-s-unfold" @click="showTable = true"></i>
                <i style="cursor: pointer" class="el-icon-menu" @click="showTable = false"></i>
            </span>
            <el-table v-show="showTable" :data="tableData" stripe style="width: 100%; padding-bottom: 60px;">
                <el-table-column prop="medId" label="ID" width="100"></el-table-column>
                <el-table-column prop="medName" label="名称" width="200"></el-table-column>
                <el-table-column prop="price" label="售价" width="120"></el-table-column>
                <el-table-column prop="quantity" label="库存" width="120"></el-table-column>
                <el-table-column label="生产日期" width="170">
                    <template slot-scope="scope">
                        <p>{{scope.row.productDate | timeFormat}}</p>
                    </template>
                </el-table-column>
                <el-table-column label="到期时间" width="170">
                    <template slot-scope="scope">
                        <p>{{scope.row.shelfLife | timeFormat}}</p>
                    </template>
                </el-table-column>
                <el-table-column prop="sale" label="上架" width="100px">
                    <template slot-scope="scope">
                        <el-switch
                          v-model="scope.row.sale"
                          active-color="#13ce66"
                          inactive-color="#ff4949"
                          @change="updateIsUse(scope.row)">
                        </el-switch>
                    </template>
                </el-table-column>
                <el-table-column label="操作">
                    <template slot-scope="scope">
                        <el-button type="primary" icon="el-icon-edit" circle size="mini" @click="handleEdit(scope.row)"></el-button>
                        <el-button type="danger" icon="el-icon-delete" circle size="mini" @click="handleDel(scope.row)"></el-button>
                        <el-dialog title="编辑药品信息" :visible.sync="dialogVisible">
                            <el-form :model="ruleForm" :rules="rules" ref="ruleForm"
                            label-width="90px" class="demo-ruleForm">
                                <el-form-item label="ID" prop="medId">
                                    <el-input v-model="ruleForm.medId" disabled></el-input>
                                </el-form-item>
                                <el-form-item label="品名" prop="medName">
                                    <el-input v-model="ruleForm.medName"></el-input>
                                </el-form-item>
                                <el-form-item label="药品类别" required>
                                    <el-select v-model="ruleForm.type" filterable placeholder="请选择药品种类">
                                        <el-option
                                          v-for="item in options"
                                          :key="item.value"
                                          :label="item.label"
                                          :value="item.value">
                                        </el-option>
                                    </el-select>
                                </el-form-item>
                                <el-form-item label="功效" prop="efficacy">
                                    <el-input type="textarea" :rows="2" v-model="ruleForm.efficacy"></el-input>
                                </el-form-item>
                                <el-form-item label="服用方法" prop="takingMethod">
                                    <el-input v-model="ruleForm.takingMethod"></el-input>
                                </el-form-item>
                                <el-form-item label="生产/到期" required>
                                    <el-col :span="11">
                                      <el-date-picker type="date" placeholder="生产日期"
                                      v-model="ruleForm.productDate" style="width: 100%;"></el-date-picker>
                                    </el-col>
                                    <el-col class="line" style="text-align: center" :span="2">-</el-col>
                                    <el-col :span="11">
                                      <el-date-picker type="date" placeholder="到期日期"
                                      v-model="ruleForm.shelfLife" style="width: 100%;"></el-date-picker>
                                    </el-col>
                                </el-form-item>
                                <el-form-item label="售价" prop="price">
                                    <el-input-number v-model="ruleForm.price" placeholder="0.00" :precision="2" :step="0.01" :min="0"></el-input-number>
                                </el-form-item>
                            </el-form>
                            <span slot="footer" class="dialog-footer">
                                <el-button @click="dialogVisible = false">取 消</el-button>
                                <el-button type="primary" @click="updateMed">确 定</el-button>
                            </span>
                        </el-dialog>
                    </template>
                </el-table-column>
            </el-table>
            <div v-show="!showTable" style="margin:25px 0 40px;width:100%;list-style:none;display: flex;flex-flow: wrap row;justify-content: flex-start;">
                <div style="浅色投影 box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);margin:10px 10px;width: 18%;
                background-color:#d9edf3;padding: 8px;box-sizing: border-box;border-radius: 6px"
                v-for="item in tableData"
                >
                    <div style="background-color:#fff;"><img style="width: 100%" :src='"http://localhost:8080/MedicineManageSystem/upload/" + item.picUrl' alt=""></div>
                    <div style="color: #6e5773">
                        <p style="font-size: 18px;color: #363062">{{item.medName}}</p>
                        <p>药效：{{item.efficacy}}</p>
                        <p>服用方法：{{item.takingMethod}}</p>
                        <p>价格：{{item.price}}</p>
                        <p>生产日期：{{item.productDate | timeFormat}}</p>
                        <p>到期日期：{{item.shelfLife | timeFormat}}</p>
                        <p>库存：{{item.quantity}}</p>
                    </div>
                </div>
            </div>
            
        </div>
    `,
    data(){
        return {
            showTable: true,
            dialogVisible: false,
            // 修改框
            options: [],
            tableData: [],
            ruleForm: {},
            medType: '',
            rules: {
                medName: [
                    { required: true, message: '请输入品名', trigger: 'blur'}
                ],
                efficacy: [
                    { required: true, message: '请输入功效', trigger: 'blur'}
                ],
                takingMethod: [
                    { required: true, message: '请输入服用方法', trigger: 'blur'},
                ],
                productDate: [
                    { required: true, message: '请选择生产日期', trigger: 'change'},
                ],
                shelfLife: [
                    { required: true, message: '请选择到期日期', trigger: 'change'},
                ],
                price: [
                    { required: true, message: '请输入售价'}
                ],
            }
        }
    },
    filters:{
        timeFormat(val){
            let date = new Date(val);
            return date.getFullYear() + '年' + (date.getMonth() + 1) + '月' + date.getDate() + '日';
        }
    },
    methods:{
        search(){
            // 获取药品信息
            axios.get('medicine?type=' + this.medType)
                .then(res=>{
                    this.tableData = res.data;

                    this.tableData.some(item=>{
                        item.productDate *= 1000;
                        item.shelfLife *= 1000;
                    })
                });
        },
        handleDel(row){
            this.$confirm('此操作将永久删除该记录, 是否继续?', '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning'
            }).then(() => {
                axios.delete('medicine?medId=' + row.medId)
                    .then(res=>{
                        // 若删除失败
                        if (!res.data)
                        {
                            this.$message({
                                type: 'error',
                                message: '删除失败!'
                            });
                        }
                        else
                        {
                            this.$message({
                                type: 'success',
                                message: '删除成功!'
                            });
                        }
                        // 刷新数据
                        this.search();
                    })
            }).catch(() => {
                this.$message({
                    type: 'info',
                    message: '已取消删除'
                });
            });

        },
        handleEdit(row){
            this.ruleForm = row;
            this.dialogVisible = true;
        },
        updateMed(){
            // 更新药品信息
            let str = JSON.stringify(this.ruleForm);
            let obj = JSON.parse(str);
            obj.productDate = new Date(obj.productDate).getTime() / 1000;
            obj.shelfLife = new Date(obj.shelfLife).getTime() / 1000;

            axios.put('medicine', obj)
                .then(res=>{
                    // 若修改失败
                    if (!res.data)
                    {
                        this.$message({
                            type: 'error',
                            message: '修改失败!'
                        });
                    }
                    else
                    {
                        this.$message({
                            type: 'success',
                            message: '修改成功!'
                        });
                    }

                    // 刷新数据
                    this.search();
                });

            this.dialogVisible = false;
        },
        updateIsUse(row){
            this.ruleForm = row;
            this.updateMed();
        }
    },
    mounted(){
        // 获取药品类型信息
        axios.get('medicinetype')
            .then(res=>{
                this.options = res.data;
            });
    }
}

// 新增药品
const AddMed = {
    template: `
        <el-form :model="ruleForm" :rules="rules" ref="ruleForm" 
        label-width="90px" 
        style="width: 500px; padding: 10px 30px; box-sizing: border-box;
        box-shadow: 0 2px 6px rgba(0, 0, 0, .12), 0 0 6px rgba(0, 0, 0, .04); 
        background-color:#d9e8f3;border-radius: 4px" class="demo-ruleForm">
            <p style="text-align: center; font-size: 18px;line-height: 36px;">药品信息添加</p>
            <el-form-item label="品名" prop="medName">
                <el-input v-model="ruleForm.medName"></el-input>
            </el-form-item>
            <el-form-item label="药品类别" required>
                <el-select v-model="ruleForm.type" filterable placeholder="请选择药品种类">
                    <el-option
                      v-for="item in options"
                      :key="item.value"
                      :label="item.label"
                      :value="item.value">
                    </el-option>
                </el-select>
            </el-form-item>
            <el-form-item label="功效" prop="efficacy">
                <el-input type="textarea" :rows="1" v-model="ruleForm.efficacy"></el-input>
            </el-form-item>
            <el-form-item label="服用方法" prop="takingMethod">
                <el-input v-model="ruleForm.takingMethod"></el-input>
            </el-form-item>
            <el-form-item label="生产/到期" required>
                <el-col :span="11">
                  <el-date-picker type="date" placeholder="生产日期"
                  v-model="ruleForm.productDate" style="width: 100%;"></el-date-picker>
                </el-col>
                <el-col class="line" style="text-align: center" :span="2">-</el-col>
                <el-col :span="11">
                  <el-date-picker type="date" placeholder="到期日期"
                  v-model="ruleForm.shelfLife" style="width: 100%;"></el-date-picker>
                </el-col>
            </el-form-item>
            <el-form-item label="售价" prop="price">
                <el-input-number v-model="ruleForm.price" placeholder="0.00" :precision="2" :step="0.01" :min="0"></el-input-number>
            </el-form-item>
            <el-form-item label="上传图片" prop="picture">
                <el-upload
                  ref="upload"
                  class="upload-demo"
                  action="uploadMedInfo"
                  :on-success="upFile"
                  :limit="1"
                  :data="ruleForm"
                  :on-exceed="handleExceed"
                  :before-upload="beforeAvatarUpload"
                  :auto-upload="false">
                  <el-button size="small" type="primary">点击选择图片</el-button>
                  <div slot="tip" class="el-upload__tip">只能上传jpg文件</div>
                </el-upload>
            </el-form-item>
            <el-form-item>
                <el-button round type="primary" @click="submitForm('ruleForm')">提交</el-button>
                <el-button round @click="resetForm('ruleForm')">重置</el-button>
             </el-form-item>
        </el-form>
    `,
    data(){
        return {
            options: [],
            ruleForm: {},
            rules: {
                medName: [
                    { required: true, message: '请输入品名', trigger: 'blur'}
                ],
                efficacy: [
                    { required: true, message: '请输入功效', trigger: 'blur'}
                ],
                takingMethod: [
                    { required: true, message: '请输入服用方法', trigger: 'blur'},
                ],
                productDate: [
                    { required: true, message: '请选择生产日期', trigger: 'change'},
                ],
                shelfLife: [
                    { required: true, message: '请选择到期日期', trigger: 'change'},
                ],
                price: [
                    { required: true, message: '请输入售价'}
                ],
            }
        }
    },

    methods: {
        upFile(res){
            if (res) {
                this.$message.success("添加成功");
            }
            else
            {
                this.$message.error("添加失败");
            }
        },
        beforeAvatarUpload(file) {
            const isJPG = file.type === 'image/jpeg';
            const isPNG = file.type === 'image/png';


            if (!isJPG && !isPNG) {
                this.$message.error('上传头像图片只能是 JPG或PNG 格式!');
            }

            return isJPG || isPNG;
        },
        handleExceed(){
            this.$message({
                type: 'error',
                message: '数量超出限制'
            });
        },
        submitForm(formName) {
            this.$refs[formName].validate((valid) => {
                if (valid) {
                    this.ruleForm.productDate = new Date(this.ruleForm.productDate).getTime() / 1000;
                    this.ruleForm.shelfLife = new Date(this.ruleForm.shelfLife).getTime() / 1000;

                    this.$refs.upload.submit();
                }
            });
        },
        resetForm(formName) {
            this.$refs[formName].resetFields();
        }
    },
    mounted(){
        // 获取药品类型信息
        axios.get('medicinetype')
            .then(res=>{
                this.options = res.data;
            });
    }
}

// 添加库存
const PutStorage = {
    template: `
        <el-form :model="ruleForm" ref="ruleForm" 
        label-width="90px" 
        style="width: 450px; padding: 10px 30px; box-sizing: border-box;
        box-shadow: 0 2px 6px rgba(0, 0, 0, .12), 0 0 6px rgba(0, 0, 0, .04); 
        background-color:#d9e8f3;border-radius: 4px" class="demo-ruleForm">
            <p style="text-align: center; font-size: 18px;line-height: 36px;">添加库存</p>
            <el-form-item label="入库药品" prop="medId" required>
                <el-select v-model="ruleForm.medId" filterable placeholder="请选择">
                    <el-option
                      v-for="item in options"
                      :key="item.value"
                      :label="item.label"
                      :value="item.value">
                      <span style="float: left">{{ item.label }}</span>
                      <span style="float: right; color: #8492a6; font-size: 13px">{{ item.value }}</span>
                    </el-option>
                </el-select>
            </el-form-item>
            <el-form-item label="成本价" prop="cost" required>
                <el-input-number v-model="ruleForm.cost" placeholder="0.00" :precision="2" :step="0.01" :min="0"></el-input-number>
            </el-form-item>
            <el-form-item label="新增库存" prop="quantity" required>
                <el-input-number v-model="ruleForm.quantity" placeholder="0" :precision="0" :step="1" :min="0"></el-input-number>
            </el-form-item>
            <el-form-item label="录入日期" prop="date" required>
                <el-col :span="11">
                  <el-date-picker type="date" placeholder="请选择"
                  v-model="ruleForm.date" style="width: 100%;"></el-date-picker>
                </el-col>
            </el-form-item>
            <el-form-item>
                <el-button type="primary" @click="submitForm('ruleForm')">提交</el-button>
                <el-button @click="resetForm('ruleForm')">重置</el-button>
             </el-form-item>
        </el-form>
    `,
    data(){
        return {
            medId: '',
            options: [],
            ruleForm: {},
        }
    },
    methods: {
        submitForm(formName) {
            this.$refs[formName].validate((valid) => {
                if (valid) {
                    // 获取当前操作用户
                    let loginStr = window.sessionStorage.getItem("manager_info");
                    let loginInfo = JSON.parse(loginStr);
                    this.ruleForm.hand_person = loginInfo.login_name;

                    this.ruleForm.date = new Date(this.ruleForm.date).getTime() / 1000;

                    axios.post('putsto', this.ruleForm)
                        .then(res=>{
                            if (res.data)
                            {
                                this.$message({
                                    type: 'success',
                                    message: '添加成功!'
                                });
                            }else
                            {
                                this.$message({
                                    type: 'error',
                                    message: '添加失败!'
                                });
                            }
                        })

                    console.log(this.ruleForm);
                }
            });
        },
        resetForm(formName) {
            this.$refs[formName].resetFields();
        }
    },
    mounted(){
        axios.get('mid')
            .then(res=>{
                this.options = res.data;
            })
    }
}

// 库存添加记录
const StorageHistory = {
    template: `
        <div>
            <span>开始时间</span>
            <el-date-picker
              v-model="begin"
              type="date"
              placeholder="选择日期">
            </el-date-picker>
            <span>结束时间</span>
            <el-date-picker
              v-model="end"
              type="date"
              placeholder="选择日期">
            </el-date-picker>
            <el-button type="primary" round @click="search">查询</el-button>
            <el-table :data="tableData" style="width: 100%;padding-bottom: 60px">
              <el-table-column prop="eno" label="库存ID" width="100"></el-table-column>
              <el-table-column prop="med_id" label="药品ID" width="100"></el-table-column>
              <el-table-column prop="quantity" label="入库数量" width="100"></el-table-column>
              <el-table-column prop="cost" label="成本/元" width="100"></el-table-column>
              <el-table-column prop="med_name" label="药品名称" width="200"></el-table-column>
              <el-table-column label="入库日期" width="150">
                <template slot-scope="scope">
                      <p>{{scope.row.date | timeFormat}}</p>
                </template>
              </el-table-column>
              <el-table-column prop="login_name" label="经办人ID" width="80"></el-table-column>
              <el-table-column prop="emp_name" label="经办人"></el-table-column>
              <el-table-column label="操作">
                    <template slot-scope="scope">
                        <el-button type="danger" icon="el-icon-delete" circle size="mini" @click="handleDel(scope.row)"></el-button>
                    </template>
              </el-table-column>
            </el-table>
        </div>
    `,
    data(){
      return{
          begin: '',
          end: '',
          tableData: []
      }
    },
    filters:{
        timeFormat(val){
            let date = new Date(val * 1000);
            return date.getFullYear() + '年' + (date.getMonth() + 1) + '月' + date.getDate() + '日';
        }
    },
    methods:{
        handleDel(row){
            this.$confirm('此操作将永久删除该记录, 是否继续?', '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning'
            }).then(() => {
                axios.delete('putsto?eno=' + row.eno)
                    .then(res=>{
                        // 若删除失败
                        if (!res.data)
                        {
                            this.$message({
                                type: 'error',
                                message: '删除失败!'
                            });
                        }
                        else
                        {
                            this.$message({
                                type: 'success',
                                message: '删除成功!'
                            });
                        }
                        // 刷新数据
                        this.search();
                    })
            }).catch(() => {
                this.$message({
                    type: 'info',
                    message: '已取消删除'
                });
            });

        },
        search() {

            if (this.begin && this.end && this.begin <= this.end)
            {
                let begin = new Date(this.begin).getTime() / 1000;
                let end = new Date(this.end).getTime() / 1000;

                axios.get('putsto?begin='+begin+'&end=' + end)
                    .then(res=>{
                        this.tableData = res.data;
                        this.$message.success("获取数据成功");
                    })
            }
            else if (this.begin > this.end)
            {
                this.$message.error("开始日期不能大于结束日期");
            }
            else
            {
                this.$message.error("开始和结束日期不能为空");
            }
        }
    }
}

// 订单记录
const OrderHistory = {
    template: `
        <div>
            <div v-show="showTable">
                <span>开始时间</span>
                <el-date-picker
                  v-model="begin"
                  type="date"
                  placeholder="选择日期">
                </el-date-picker>
                <span>结束时间</span>
                <el-date-picker
                  v-model="end"
                  type="date"
                  placeholder="选择日期">
                </el-date-picker>
            <el-button type="primary" round @click="search">查询</el-button>
            </div>
            <span style="float: right;margin-right: 20px">
                <i style="cursor: pointer;" class="el-icon-s-unfold" @click="showTable = true"></i>
                <i style="cursor: pointer" class="el-icon-menu" @click="showMap"></i>
            </span>
            <el-table v-show="showTable" :data="tableData" style="width: 100%;padding-bottom: 60px;">
              <el-table-column prop="order_id" label="订单ID" width="100"></el-table-column>
              <el-table-column prop="med_id" label="药品ID" width="100"></el-table-column>
              <el-table-column prop="quantity" label="数量" width="100"></el-table-column>
              <el-table-column prop="cost" label="成本/元" width="100"></el-table-column>
              <el-table-column prop="price" label="售价/元" width="100"></el-table-column>
              <el-table-column prop="med_name" label="药品名称" width="200"></el-table-column>
              <el-table-column label="日期" width="150">
                <template slot-scope="scope">
                      <p>{{scope.row.date | timeFormat}}</p>
                </template>
              </el-table-column>
              <el-table-column prop="login_name" label="经办人ID" width="80"></el-table-column>
              <el-table-column prop="emp_name" label="经办人"></el-table-column>
            </el-table>
            <div v-show="!showTable" style="margin: 20px 0 0 20px;">
                <div id="incomeAnalyze" style="width: 800px;height:535px;"></div>
            </div>
        </div>
    `,
    data(){
        return{
            showTable: true,
            begin: '',
            end: '',
            tableData: []
        }
    },
    filters:{
        timeFormat(val){
            let date = new Date(val * 1000);
            return date.getFullYear() + '年' + (date.getMonth() + 1) + '月' + date.getDate() + '日';
        }
    },
    methods:{
        search() {
            if (this.begin && this.end && this.begin <= this.end)
            {
                let begin = new Date(this.begin).getTime() / 1000;
                let end = new Date(this.end).getTime() / 1000;

                axios.get('order?begin='+begin+'&end=' + end)
                    .then(res=>{
                        this.tableData = res.data;
                        this.$message.success("获取数据成功");
                    })
            }
            else if (this.begin > this.end)
            {
                this.$message.error("开始日期不能大于结束日期");
            }
            else
            {
                this.$message.error("开始和结束日期不能为空");
            }
        },
        initIncomeAnalyze(costSum, priceSum, profit){
            let myChart = echarts.init(document.getElementById('incomeAnalyze'));

            // 指定图表的配置项和数据
            let option = {
                tooltip: {
                    trigger: 'axis',
                    axisPointer: {
                        type: 'cross',
                        crossStyle: {
                            color: '#999'
                        }
                    }
                },
                toolbox: {
                    feature: {
                        dataView: {show: true, readOnly: false},
                        magicType: {show: true, type: ['line', 'bar']},
                        restore: {show: true},
                        saveAsImage: {show: true}
                    }
                },
                legend: {
                    data: ['成本', '销售额', '收益']
                },
                xAxis: [
                    {
                        type: 'category',
                        data: ['1月', '2月', '3月', '4月', '5月', '6月', '7月', '8月', '9月', '10月', '11月', '12月'],
                        axisPointer: {
                            type: 'shadow'
                        }
                    }
                ],
                yAxis: [
                    {
                        type: 'value',
                        name: '成本',
                        min: 0,
                        max: 40000,
                        interval: 5000,
                        axisLabel: {
                            formatter: '{value}元'
                        }
                    },
                    {
                        type: 'value',
                        name: '收益',
                        min: 0,
                        max: 15000,
                        interval: 10000,
                        axisLabel: {
                            formatter: '{value}元'
                        }
                    }
                ],
                series: [
                    {
                        name: '成本',
                        type: 'bar',
                        data: costSum
                    },
                    {
                        name: '销售额',
                        type: 'bar',
                        data: priceSum
                    },
                    {
                        name: '收益',
                        type: 'line',
                        yAxisIndex: 1,
                        data: profit
                    }
                ]
            };


            // 使用刚指定的配置项和数据显示图表。
            myChart.setOption(option);
        },
        showMap(){
            this.showTable = false;
            // 获取数据
            axios.get('income?year=2020')
                .then(res=>{
                   let data = res.data;
                   this.initIncomeAnalyze(data.costSum, data.priceSum, data.profit);
                });
        }
    }
}

// 药品类别
const MedType = {
    template: `
        <div>
            <el-button type="primary" round @click="search">查询</el-button>
            <el-button type="primary" round @click="add" :disabled="isAddDisabled">新增</el-button>
            <el-table :data="tableData" style="width: 100%; padding-bottom: 60px;">
              <el-table-column prop="value" label="药品类型" width="120"></el-table-column>
              <el-table-column prop="label" label="类型名称" width="200"></el-table-column>
              <el-table-column label="操作" width="120">
                    <template slot-scope="scope">
                        <el-button type="primary" icon="el-icon-edit" circle size="mini" @click="handleEdit(scope.row)"></el-button>
                        <el-dialog title="编辑药品类型" :visible.sync="dialogVisible">
                            <el-form :model="ruleForm" :rules="rules" ref="ruleForm"
                            label-width="90px" class="demo-ruleForm">
                                <el-form-item label="药品类型" prop="value">
                                    <el-input v-model="ruleForm.value" disabled></el-input>
                                </el-form-item>
                                <el-form-item label="药品名称" prop="label">
                                    <el-input v-model="ruleForm.label"></el-input>
                                </el-form-item>
                            </el-form>
                            <span slot="footer" class="dialog-footer">
                                <el-button @click="dialogVisible = false">取 消</el-button>
                                <el-button type="primary" @click="update">确 定</el-button>
                            </span>
                        </el-dialog>
                        <el-dialog title="添加药品类型" :visible.sync="dialogVisibleAdd">
                            <el-form :model="ruleForm1" :rules="rules" ref="ruleForm"
                            label-width="90px" class="demo-ruleForm">
                                <el-form-item label="药品类型" prop="value">
                                    <el-input v-model="ruleForm1.value"></el-input>
                                </el-form-item>
                                <el-form-item label="药品名称" prop="label">
                                    <el-input v-model="ruleForm1.label"></el-input>
                                </el-form-item>
                            </el-form>
                            <span slot="footer" class="dialog-footer">
                                <el-button @click="dialogVisibleAdd = false">取 消</el-button>
                                <el-button type="primary" @click="addtype">确 定</el-button>
                            </span>
                        </el-dialog>
                    </template>
              </el-table-column>
            </el-table>
        </div>
    `,
    data(){
        return{
            isAddDisabled: true,
            dialogVisible: false,
            dialogVisibleAdd: false,
            begin: '',
            end: '',
            tableData: [],
            ruleForm: {},
            ruleForm1: {},
            rules: {
                value: [
                    { required: true, message: '药品类型不能为空', trigger: 'blur'}
                ],
                label: [
                    { required: true, message: '药品名称不能为空', trigger: 'blur'}
                ],
            }
        }
    },
    methods:{
        update(){
            // 更新药品信息
            let data = {type: this.ruleForm.value, typeName: this.ruleForm.label };
            axios.put('medicinetype', data)
                .then(res=>{
                    if (res.data)
                    {
                        this.$message.success("更新成功");
                        // 更新数据
                        this.search();
                    }
                    else
                    {
                        this.$message.error("更新失败");
                    }

                    this.dialogVisible = false;
                })
        },
        handleEdit(row){
            this.ruleForm = row;
            this.dialogVisible = true;
        },
        search() {
            if (this.isAddDisabled){
                this.isAddDisabled = false;
            }

            axios.get('medicinetype')
                .then(res=>{

                    if (res.data)
                    {
                        this.tableData = res.data;
                    }
                    else
                    {
                        this.$message.error("获取数据失败");
                    }
                })
        },
        add(){
            // 显示编辑框
            this.dialogVisibleAdd = true;
        },
        addtype(){
            // 判断是否已存在相同的药品类型
            let judge = this.tableData.some(item=>{
                return item.value === this.ruleForm1.value;
            });

            // 不存在执行添加操作
            if (!judge)
            {
                let data = {type: this.ruleForm1.value, typeName: this.ruleForm1.label };
                axios.post('medicinetype', data)
                    .then(res=>{
                        if (res.data)
                        {
                            this.$message.success("添加成功");
                        }
                        else
                        {
                            this.$message.error("添加失败");
                        }
                        // 刷新
                        this.search();
                        // 隐藏
                        this.dialogVisibleAdd = false;
                    })
            }
            else
            {
                this.$message.error("输入的ID名已存在，请重新输入");
            }

        },
        mounted(){
            this.search();
        }
    }
}

// 通过文件导入信息
const AddInfo = {
    template: `
        <el-form style="width: 300px">
            <p style="font-size: 18px;line-height: 36px;margin: 10px 0;">员工信息导入</p>
            <el-upload
                  ref="upload"
                  class="upload-demo"
                  action="uploadEmp"
                  :on-success="upFile"
                  :limit="1"
                  :on-exceed="handleExceed"
                  :before-upload="beforeAvatarUpload"
                  :auto-upload="false">
                  <el-button slot="trigger" size="small" type="primary">选取文件</el-button>
                  <el-button style="margin-left: 10px;" size="small" type="success" @click="submitUpload">提交</el-button>
                  <div slot="tip" class="el-upload__tip">只能上传csv文件，一次一个文件</div>
                </el-upload>
        </el-form>
    `,
    methods: {
        upFile(res){
            if (res) {
                this.$message.success("添加成功");
            }
            else
            {
                this.$message.error("添加失败");
            }
        },
        handleExceed(){
            this.$message({
                type: 'error',
                message: '数量超出限制'
            });
        },
        beforeAvatarUpload(file) {
            if (!file.name.endsWith('.csv')) {
                this.$message.error('上传头像图片只能是 CSV 格式!');
                return false;
            }

            return true;
        },
        submitUpload() {
            this.$refs.upload.submit();
        },
    }
}

// 根组件
const App = {
    template:  `
        <el-container class="container">
            <!--顶部-->
            <el-header id="header">
                <Header :emp_name="manager.emp_name"></Header>
            </el-header>
            
            <el-container class="container">
                <!--导航栏-->
                <el-aside width="200px" style="background-color:#dae1e7;">
                    <Side :is_manager="manager.is_manager" @show="showEmps($event)"></Side>
                </el-aside>
                
                <!--显示内容-->
                <el-main>
                    <router-view></router-view>
                </el-main>
            </el-container>
        </el-container>
    `,
    data() {
        return {
            manager: {}
        }
    },
    components:{
        Header, Side, Emps, AddEmp
    },
    methods: {
        checkLoginState(){
            // 检查是否登录
            let loginStr = window.sessionStorage.getItem("manager_info");
            let loginInfo = JSON.parse(loginStr);

            // 如果未登录
            if (loginInfo === null || loginInfo.is_login !== true)
                window.location.assign("login.html");

            this.manager = loginInfo;
        },
        showEmps(target){
            this.$router.push(target);
        }
    },
    mounted(){
        // 检查是否登录
        this.checkLoginState();
    }
}

// 路由配置
const routes = [
    { path: '/showemps', component: Emps },
    { path: '/addemp', component: AddEmp },
    { path: '/showemeds', component: Meds },
    { path: '/showeaddmed', component: AddMed },
    { path: '/showputsto', component: PutStorage },
    { path: '/showestorage', component: StorageHistory },
    { path: '/showorders', component: OrderHistory },
    { path: '/showmedtype', component: MedType },
    { path: '/addinfo', component: AddInfo },
];

// 解决ElementUI导航栏中的vue-router在3.0版本以上重复点菜单报错问题
const originalPush = VueRouter.prototype.push
VueRouter.prototype.push = function push(location) {
    return originalPush.call(this, location).catch(err => err)
}

const router = new VueRouter({
    routes // (缩写) 相当于 routes: routes
});

new Vue({
    router,
    render: h => h(App)
}).$mount('#app');