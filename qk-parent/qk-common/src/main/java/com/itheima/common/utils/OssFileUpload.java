package com.itheima.common.utils;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.ObjectMetadata;
import com.itheima.common.properties.OssProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.UUID;

@Component
public class OssFileUpload {
    @Autowired
    private OssProperties ossProperties;
    // TODO ossClient 应该交给生命周期管理, 避免资源消耗

    //文件上传
    public String upload(String fileName, InputStream inputStream) {
        //创建客户端
        OSS ossClient = new OSSClientBuilder().build(ossProperties.getEndpoint(),
                ossProperties.getAccessKeyId(), ossProperties.getAccessKeySecret());


        // 设置文件最终的路径和名称
        String uuid = UUID.randomUUID().toString();
        String objectName = uuid + "_" + fileName;

        //meta设置请求头,解决访问图片地址直接下载
        ObjectMetadata meta = new ObjectMetadata();
        meta.setContentType(getContentType(fileName.substring(fileName.lastIndexOf("."))));

        //上传
        ossClient.putObject(ossProperties.getBucketName(), objectName, inputStream, meta);

        //关闭客户端
        ossClient.shutdown();

        // 返回文件的url
        return "https://" + ossProperties.getBucketName() + "." + ossProperties.getEndpoint() + "/" + objectName;
    }

    //文件后缀处理
    private String getContentType(String FilenameExtension) {
        if (FilenameExtension.equalsIgnoreCase(".bmp")) {
            return "image/bmp";
        }
        if (FilenameExtension.equalsIgnoreCase(".gif")) {
            return "image/gif";
        }
        if (FilenameExtension.equalsIgnoreCase(".jpeg") ||
                FilenameExtension.equalsIgnoreCase(".jpg") ||
                FilenameExtension.equalsIgnoreCase(".png")) {
            return "image/jpg";
        }
        return "image/jpg";
    }

    //文件删除
    public void delete(String objectName) {
        OSS ossClient = new OSSClientBuilder().build(ossProperties.getEndpoint(),
                ossProperties.getAccessKeyId(), ossProperties.getAccessKeySecret());
        String stringBuilder = "https://" + ossProperties.getBucketName() + "." + ossProperties.getEndpoint() + "/";
        objectName = objectName.replace(stringBuilder, "");
        ossClient.deleteObject(ossProperties.getBucketName(), objectName);
        //关闭客户端
        ossClient.shutdown();
    }
}
