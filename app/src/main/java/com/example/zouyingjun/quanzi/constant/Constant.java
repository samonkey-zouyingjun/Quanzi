package com.example.zouyingjun.quanzi.constant;

/**
 * Created by j on 2016/11/18.
 */

public class Constant {
    public static final String HTML1 = "<html>\n" +
            "<head>\n" +
            "\t<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no\" />\n" +
            "\t<meta name=\"format-detection\" content=\"telephone=no\">\n" +
            "    <meta http-equiv=\"Content-Type\" content=\"text/html;charset=utf-8\">\n" +
            "<style type=\"text/css\">\n" +
            ".content img {border: solid 1px #bbbbbb;margin:10px auto;\n" +
            "display:block;clear:both;float: none !important;}\n" +
            "</style>\n" +
            "   \n" +
            "    \t<script type=\"text/javascript\">\n" +
            " \tString.prototype.trim = function() {\n" +
            "\t\treturn this.replace(/^s+/, '').replace(/s+$/,'');\n" +
            "\t};\n" +
            "\t\n" +
            "\n" +
            "\twindow.onload=function()\n" +
            "\t{\n" +
            "\t\tvar images = document.getElementsByTagName('img');\n" +
            "\t\tfor(var i=0; i<images.length; i++) {\n" +
            "\t\t\t//alert(images[i].width);\n" +
            "\t\t\tif(images[i].width>={MAX_CONTENT}){\n" +
            "\t\t\t\tif(images[i].src==\"\"){\n" +
            "\t\t\t\timages[i].setAttribute('height','0px');\n" +
            "\t\t\t\tcontinue;\n" +
            "\t\t\t}\n" +
            "\n" +
            "\t\t\tif(images[i]==document.getElementById('xg_subscibe_icon')\n" +
            "\t\t\t\t|| images[i]==document.getElementById('top_icon')\n" +
            "\t\t\t\t|| images[i]==document.getElementById('tread_icon')){\n" +
            "\t\t\t\tcontinue;\n" +
            "\t\t\t}\n" +
            "\t\t\t\n" +
            "\t\t\timages[i].removeAttribute('class');\n" +
            "\t\t\timages[i].removeAttribute('style');\n" +
            "\t\t\timages[i].removeAttribute('width');\n" +
            "\t\t\timages[i].removeAttribute('height');\n" +
            "\t\t\timages[i].removeAttribute('border');\n" +
            "\t\t\timages[i].style.display = \"block\";\n" +
            "\t\t\timages[i].style.width=\"228px\";\n" +
            "\n" +
            "\t\t\timages[i].onclick=function()   \n" +
            "           {    \n" +
            "                  window.imagelistner.openImage(this.src); \n" +
            "           }   \n" +
            "\t\t\t}\n" +
            "\t\t\t\n" +
            "\t\t}\n" +
            "\n" +
            "\t\twindow.imagelistner.onFinish(document.body.scrollHeight); \n" +
            "\n" +
            "\t}\n" +
            "\t</script> \n" +
            "<title>飞卓网络科技</title>\n" +
            "</head>\n" +
            "<body>\n" +
            "<div id=\"content\" class=\"content\" style=\"word-break: break-all; overflow:hidden;\" >" ;
    public static final String HTML2 = "</div>\n" +
            "</body>\n" +
            "</html>";
}
