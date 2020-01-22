package com.lss.universal.controller;

import com.lss.universal.service.UniversalService;
import com.google.gson.Gson;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.Data;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Data
@Api(tags = "通用接口")
@RestController
@RequestMapping("universal/")
public class UniversalController {

    private UniversalService universalService;
    private Gson gson=new Gson();

    @PostMapping("packageAndGoOnline")
    @ApiOperation("打包上线type 1-前端pc 2-后台管理pc端")
    public String packageAndGoOnline(@RequestParam("type")Integer type,@RequestParam("versionNumber") String versionNumber,@RequestParam("username")String username, @RequestParam("password")String password, @RequestPart("file") MultipartFile file ){
        return gson.toJson(universalService.packageAndGoOnline(type,versionNumber,username,password,file));
    }

    @GetMapping(value = "uploadHtml",produces = "text/html;charset=utf8")
    @ApiOperation("上传页面")
    public String uploadHtml(){
        return "<!DOCTYPE html>\n" +
                "<html>\n" +
                "<head>\n" +
                "  <meta charset=\"UTF-8\">\n" +
                "  <!-- import CSS -->\n" +
                "  <link rel=\"stylesheet\" href=\"https://unpkg.com/element-ui/lib/theme-chalk/index.css\">\n" +
                "  <style>\n" +
                "   .container{\n" +
                "       text-align: center;\n" +
                "       padding-top: 200px;\n" +
                "       width: 50%;\n" +
                "       margin: 0 auto;\n" +
                "   }\n" +
                "   .m-b-10{\n" +
                "       margin-bottom: 30px;\n" +
                "   }\n" +
                "   .display_flex{\n" +
                "     display: flex;\n" +
                "     align-items: center;\n" +
                "   }\n" +
                "   .m-r-20{\n" +
                "     margin-right: 20px;\n" +
                "   }\n" +
                "   .w-10{\n" +
                "     width: 10%;\n" +
                "   }\n" +
                "  </style>\n" +
                "</head>\n" +
                "<body>\n" +
                "  <div id=\"app\">\n" +
                "        <div class=\"container\">\n" +
                "          <el-tabs type=\"border-card\">\n" +
                "            <el-tab-pane label=\"快速上传地址\">\n" +
                "                          <!-- 爱乐地址 -->\n" +
                "                              <div class=\"m-b-10\">\n" +
                "                                快速上传地址\n" +
                "                              </div>\n" +
                "                                <el-upload\n" +
                "                                  class=\"upload-demo m-b-10\"\n" +
                "                                  drag\n" +
                "                                  ref=\"upload\"\n" +
                "                                  :auto-upload=\"false\"\n" +
                "                                  :file-list=\"fileList\"\n" +
                "                                  action=\"/universal/packageAndGoOnline\"\n" +
                "                                  name=\"file\"\n" +
                "                                  :on-success='getMsg'\n" +
                "                                  :on-error='getError'\n" +
                "                                  :data='params'\n" +
                "                                  multiple>\n" +
                "                                  <i class=\"el-icon-upload\"></i>\n" +
                "                                  <div class=\"el-upload__text\">将文件拖到此处，或<em>点击上传</em></div>\n" +
                "                                  <!-- <div class=\"el-upload__tip\" slot=\"tip\">只能上传jpg/png文件，且不超过500kb</div> -->\n" +
                "                                </el-upload>\n" +
                "                                <el-input class=\"m-b-10\" v-model=\"params.username \" placeholder=\"请输入用户名\"></el-input>\n" +
                "                                <el-input class=\"m-b-10\" v-model=\"params.password\"  placeholder=\"请输入密码\"></el-input>\n" +
                "                                \n" +
                "                                <div class=\"display_flex m-b-10\">\n" +
                "                                  <div class=\"m-r-20 w-10\">版本号</div>\n" +
                "                                  \n" +
                "                                    <el-input class=\" w-10 m-r-20\" v-model=\"value1\" @input='change(1,value1)' maxlength='1' ></el-input>\n" +
                "                                    <el-input class=\" w-10 m-r-20\" v-model=\"value2\" id='1' @input='change(2,value2)' maxlength='1' ></el-input>\n" +
                "                                    <el-input class=\" w-10 m-r-20\" v-model=\"value3\" id='2' @input='test(value3)' maxlength='1' ></el-input>\n" +
                "                                 \n" +
                "                                </div>\n" +
                "                                <el-select v-model=\"params.type\" class=\"m-b-10\" placeholder=\"请选择\">\n" +
                "                                    <el-option\n" +
                "                                      v-for=\"item in options\"\n" +
                "                                      :key=\"item.value\"\n" +
                "                                      :label=\"item.label\"\n" +
                "                                      :value=\"item.value\">\n" +
                "                                    </el-option>\n" +
                "                                </el-select>\n" +
                "                          <div>\n" +
                "                            <el-button  type=\"success\" @click=\"submitUpload\"  v-loading=\"loading\">上传到服务器</el-button>\n" +
                "                          </div>\n" +
                "            </el-tab-pane>\n" +
                "             <!-- 银行地址 -->\n" +
                "            <!-- <el-tab-pane label=\"银行快速上传地址\">\n" +
                "                         \n" +
                "                          <div class=\"m-b-10\">\n" +
                "                            银行快速上传地址\n" +
                "                          </div>\n" +
                "                            <el-upload\n" +
                "                              class=\"upload-demo m-b-10\"\n" +
                "                              drag\n" +
                "                              ref=\"upload\"\n" +
                "                              :auto-upload=\"false\"\n" +
                "                              :file-list=\"fileList\"\n" +
                "                              action=\"/upload/packageAndGoOnline\"\n" +
                "                              name=\"file\"\n" +
                "                              :on-success='getMsg'\n" +
                "                              :on-error='getError'\n" +
                "                              :data='params'\n" +
                "                              multiple>\n" +
                "                              <i class=\"el-icon-upload\"></i>\n" +
                "                              <div class=\"el-upload__text\">将文件拖到此处，或<em>点击上传</em></div>\n" +
                "                             \n" +
                "                            </el-upload>\n" +
                "                            <el-input class=\"m-b-10\" v-model=\"params.username \" placeholder=\"请输入用户名\"></el-input>\n" +
                "                            <el-input class=\"m-b-10\" v-model=\"params.password\"  placeholder=\"请输入密码\"></el-input>\n" +
                "                            <el-select v-model=\"params.type\" class=\"m-b-10\" placeholder=\"请选择\">\n" +
                "                                <el-option\n" +
                "                                  v-for=\"item in options\"\n" +
                "                                  :key=\"item.value\"\n" +
                "                                  :label=\"item.label\"\n" +
                "                                  :value=\"item.value\">\n" +
                "                                </el-option>\n" +
                "                            </el-select>\n" +
                "                      <div>\n" +
                "                        <el-button  type=\"success\" @click=\"submitUpload\"  v-loading=\"loading\">上传到服务器</el-button>\n" +
                "                      </div>              \n" +
                "            </el-tab-pane> -->\n" +
                "            \n" +
                "          </el-tabs>\n" +
                "        </div>\n" +
                "  </div>\n" +
                "</body>\n" +
                "  <!-- import Vue before Element -->\n" +
                "  <script src=\"https://unpkg.com/vue/dist/vue.js\"></script>\n" +
                "  <!-- import JavaScript -->\n" +
                "  <script src=\"https://unpkg.com/element-ui/lib/index.js\"></script>\n" +
                "  <script>\n" +
                "    new Vue({\n" +
                "      el: '#app',\n" +
                "      data: function() {\n" +
                "        return { \n" +
                "          loading:false,\n" +
                "          value1:'',\n" +
                "          value2:'',\n" +
                "          value3:'',\n" +
                "            options:[\n" +
                "                {\n" +
                "                    value: '1',\n" +
                "                    label: '1前端pc，手机端'\n" +
                "                },\n" +
                "                {\n" +
                "                    value: '2',\n" +
                "                    label: '2前端后台pc'\n" +
                "                },\n" +
                "                {\n" +
                "                    value: '3',\n" +
                "                    label: '3后端server'\n" +
                "                }\n" +
                "            ],\n" +
                "            fileList:[],\n" +
                "            params:{\n" +
                "              username:'',\n" +
                "              password:'',\n" +
                "              type:'',\n" +
                "              versionNumber:''\n" +
                "            }\n" +
                "         }\n" +
                "      },\n" +
                "      methods:{\n" +
                "        // change\n" +
                "        change(id,val){\n" +
                "          console.log(val)\n" +
                "          let d1=document.getElementById(id-1);\n" +
                "          let d2=document.getElementById(id);\n" +
                "          console.log(d2)\n" +
                "          let reg=/[0-9]/          \n" +
                "          if(val!='' && reg.test(val)){\n" +
                "              d2.focus()\n" +
                "          }else{\n" +
                "            this.$message.error('请输入正确的版本号');\n" +
                "            \n" +
                "          }\n" +
                "        },\n" +
                "        test(val){\n" +
                "          let reg=/[0-9]/    \n" +
                "          if(!reg.test(val)){\n" +
                "            this.$message.error('请输入正确的版本号');\n" +
                "            return false\n" +
                "          }\n" +
                "          else{\n" +
                "            return true\n" +
                "          }\n" +
                "        },\n" +
                "                // 上传\n" +
                "        submitUpload(){\n" +
                "            if(this.params.username==null || this.params.password==null ||this.params.type==null ){\n" +
                "                this.$message.error('请输入参数');\n" +
                "            }else{\n" +
                "              if(this.test(this.value1) || this.test(this.value1 ) || this.test(this.value1 )){\n" +
                "                  console.log(123)\n" +
                "                  this.params.versionNumber=`${this.value1}.${this.value2}.${this.value3}`\n" +
                "                  console.log( this.params)\n" +
                "              }else{\n" +
                "                return\n" +
                "              }\n" +
                "             \n" +
                "              console.log( this.params)\n" +
                "              this.loading=true\n" +
                "              setTimeout(res=>{\n" +
                "                this.$refs.upload.submit();\n" +
                "              },500)\n" +
                "            }\n" +
                "        },\n" +
                "        getMsg(res){\n" +
                "          console.log(res)\n" +
                "          if(res.status==200){\n" +
                "            this.loading=false\n" +
                "          this.$message.success('上传成功');\n" +
                "          this.fileList=[]\n" +
                "          this.value1=''\n" +
                "          this.value2=''\n" +
                "          this.value3=''\n" +
                "           this.params={\n" +
                "            username:'',\n" +
                "              password:'',  \n" +
                "              type:'',\n" +
                "              versionNumber:''\n" +
                "           }\n" +
                "          }else{\n" +
                "            this.loading=false\n" +
                "            this.$message.error(res.msg)\n" +
                "            this.fileList=[]\n" +
                "          }\n" +
                "\n" +
                "        },\n" +
                "        getError(res){\n" +
                "          this.loading=false\n" +
                "          console.log(res)\n" +
                "\n" +
                "        }\n" +
                "\n" +
                "      },\n" +
                "    })\n" +
                "  </script>\n" +
                "</html>\n";
    }

    @GetMapping("test")
    @ApiOperation("测试注入成功")
    public String test(){
        return "成功";
    }
}
