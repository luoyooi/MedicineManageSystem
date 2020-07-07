var app = new Vue({
    data() {
        return {
            ruleForm: {
                login_name: '',
                login_password: '',
            },
            // 规则声明
            rules: {
                login_name: [
                    { required: true, message: '请输入账号', trigger: 'blur' },
                    { min: 3, max: 8, message: '长度在 6 到 10 个字符'}
                ],
                login_password: [
                    { required: true, message: '请输入密码', trigger: 'blur' },
                    { min: 6, max: 16, message: '长度在 8 到 16 个字符'}
                ]
            }
        };
    },
    methods: {
        // 提交登录信息
        submitForm(formName) {
            this.$refs[formName].validate((valid) => {
                if (valid) {
                    // 验证登录密码
                    axios.post('login', this.ruleForm)
                        .then(data=>{
                            let result = data.data;

                            if (result.is_login)
                            {
                                // 登录成功，保存成功信息
                                window.sessionStorage.setItem("manager_info", JSON.stringify(result));
                                // 跳转到主页
                                window.location.assign("index.html");
                            }
                            else
                            {
                                // 登录失败
                                this.$message({
                                    type: 'error',
                                    message: '登录失败'
                                });
                            }
                        })

                } else {
                    return false;
                }
            });
        },
        // 重置登录信息
        resetForm(formName) {
            this.$refs[formName].resetFields();
        }
    }
}).$mount('#app');