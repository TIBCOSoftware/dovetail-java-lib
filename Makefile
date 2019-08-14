SCRIPTS_PATH      := scripts

.PHONY: tag
tag: 
	$(SCRIPTS_PATH)/tag.sh ${BUILD_BRANCH} ${BUILD_NUM}

.PHONY: is-prerelease
is-prerelease: 
	@ $(SCRIPTS_PATH)/prerelease.sh ${BUILD_BRANCH}

.PHONY: release-notes
release-notes: 
	@ $(SCRIPTS_PATH)/release-notes.sh

.PHONY: artifacts
artifacts: 
	@ $(SCRIPTS_PATH)/artifacts.sh ${BUILD_TAG}