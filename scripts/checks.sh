#!/bin/bash
build_tag=$1
is_prerelease=$2
snapshot="SNAPSHOT"

echo "Checking that version is correct ..."
current_version=$(xmllint --xpath '/*[local-name()="project"]/*[local-name()="version"]/text()' pom.xml)
echo "found current version ${current_version}"
    
if [ $is_prerelease == true ];then
    echo "We are doing a prerelease, version should be a SNAPSHOT"
    if [[ ${current_version} == *$snapshot ]];then
        echo "The version is a SNAPSHOT! we are good ..."
    else
        echo "Oh no! we should have a SNAPSHOT version here"
        exit 1
    fi
else
    echo "We are doing a release, version should NOT be a SNAPSHOT"
    if [[ ! ${current_version} == *$snapshot ]];then
        echo "The version is NOT a SNAPSHOT! we are good ..."
    else
        echo "Oh no! we should have NOT a SNAPSHOT version here"
        exit 1
    fi
fi

current_valid_version="v"${current_version}
echo "Comparing current tag ${build_tag} and version ${current_valid_version}"
if [[  ${build_tag} == $current_valid_version* ]];then
    echo "Versions in pom.xml and version.txt versions look ok"
else
    echo "Oh no! Versions in pom.xml and version.txt versions do not look ok"
    exit 1
fi
