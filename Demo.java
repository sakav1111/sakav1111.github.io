ProcessExcelData.java
----------------
processExcelData
     // If input file contains User data
            if (dataType != null && dataType.equalsIgnoreCase("u")) {
              ProcessExcelUserData.setContext(context);
              ProcessExcelUserData.setProperties(properties);
              if (!ProcessExcelUserData.processExcelUserData(row, rejectFile, userName)) {
                rejected++;
              }
              // If input file contains Project data
            } 

ProcessExcelUserData.java			
------------------------------------
			
			userProfileDto.setTrained(user.getTrained());
			userProfileDto.setEmailOption("Y");
			userProfileDto.setDataSource("BATCHLOAD");
			userProfileDto.setCustomEmailFlag(user.getCustomEmailTemplate());
			
			
			UserProfileDataLoadService userProfiledataLoadService = new UserProfileDataLoadServiceImpl();
			userProfiledataLoadService.setServiceWsdlUrl(properties.getUserProfileDataLoadServicePrefixUrl()
					+ properties.getUserProfileDataLoadServiceEndPointUrl());
					
UserProfileDataLoadServiceImpl.java
-------------------------------------------
  public UserDataResponse userDataLoad(UserProfileDto userProfileDtoList) throws Exception {

    LOG.debug("UserProfileDataLoadServiceImpl class in assertIdentity() method");

    UserProfileDataEndpointService userProfileDataEndpointService = getUserProfileDataEndPointService();

    UserDataResponse userDataResponse = userProfileDataEndpointService.getUserProfileDataLoadServicePort().userdataLoad(userProfileDtoList);

    return userDataResponse;
  }
  
  sso-services
=========================
@WebService(name = "UserProfileDataLoadService")
public class UserProfileDataEndpoint extends SpringBeanAutowiringSupport



	public UserDataResponse userdataLoad(@WebParam(name = "userProfileDataDtoList") UserProfileDto userProfileDto)
	throws ProvisionDataException {
		return userProfileService.saveUserpProfile(userProfileDto);
	}
	
	
	
provision-sso-v3
-----------------
UserManager.java


  private static final String BATCH_LOADER_GROUPS = "perceptive.mytrials.batchloader";
  
  
  public UserDataResponse saveUserpProfile(UserProfileDto userProfileDto) throws ProvisionDataException {
	  
	  
	            eRoleProductVRoleIdList = validateAndGetERoleProductVRoleIdListByERoleName(
              userProfileDto.geteClinicalRole(), projectId, errList, userProfileDto.getUserName(),
              userProfileDto.getProjectName(), userProfileDto.getProjectType(), userProfileDto.isInternalUser(),
              isUserMemberOfGroup(userProfileDto.getUserName(), BATCH_LOADER_GROUPS), dataSource);
			  
			  
			      if (!(isInternalUser || (isBatchLoaderGroupUser && "BATCHLOAD".equalsIgnoreCase(dataSource)))
        && CollectionUtils.isNotEmpty(rolesList)
        && YES_FLAG.equalsIgnoreCase(rolesList.get(0).getPrivFlag())) {
      errList.add(context.getMessage("messages.privileged.flag.true", null, Locale.US));
    }


  private boolean isUserMemberOfGroup(String userName, String groupName) {
    boolean isGroupUser = false;
    try {
      LOG.debug("Getting groups from ISAMUserService for User: " + userName);
      Set<String> isamUserMemberShips = isamUserService.getUserGroups(userName);
      if (isamUserMemberShips != null && isamUserMemberShips.contains(groupName)) {
        isGroupUser = true;
      }
    } catch (ISAMWrapperException exception) {
      LOG.error("Exception Occurred While Retriving User Groups", exception);
    }
    return isGroupUser;
  }
	  
	  
  }
	============================= Project ========================
	
	ProcessExcelData.java
	else if (dataType != null && dataType.equalsIgnoreCase("p")) {
              ProcessExcelProjectData.setContext(context);
              ProcessExcelProjectData.setProperties(properties);
              if (!ProcessExcelProjectData.processProjectData(row, rejectFile, userName)) {
                rejected++;
              }
	