#!/bin/bash

SONAR_KEY="sonar:microservice-spirit"
STATUS=$(curl -qsSL "http://localhost:9000/api/qualitygates/project_status?projectKey=${SONAR_KEY}" |\
    jq .projectStatus.status | grep -o "\w\+")
if [ "${STATUS}" = "ERROR" ]; then
    echo "代码质量扫描未通过，请检查 http://localhost:9000/overview?id=${SONAR_KEY} 的扫描报告并修复！"
    exit -1
fi
echo "代码质量扫描完成！"
