FROM  java:8
#java:8
#openjdk:8-jdk-alpine
# VOLUME 指定了临时文件目录为/tmp。
# 其效果是在主机 /var/lib/docker 目录下创建了一个临时文件，并链接到容器的/tmp
VOLUME /tmp
# time zone
RUN echo "Asia/Chongqing"
# 将jar包添加到容器中并更名为app.jar
ADD engineer.jar engineer.jar
# 运行jar包
RUN bash -c 'touch /engineer.jar'
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/engineer.jar","--spring.profiles.active=pro"]
