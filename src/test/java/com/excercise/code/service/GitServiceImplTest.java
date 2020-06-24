package com.excercise.code.service;

import static junit.framework.TestCase.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.excercise.code.client.GitClient;
import com.excercise.code.domain.RepositoriesInfo;
import com.excercise.code.domain.Users;
import com.excercise.code.exception.CTSBusinessException;
import com.excercise.code.service.impl.GitServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
public class GitServiceImplTest {

  @InjectMocks
  private GitServiceImpl gitService;

  @Mock
  GitClient gitClient;

  @Before
  public void setUp() throws Exception {
    MockitoAnnotations.initMocks(this);
  }

  @Test
  public void shouldAbleTogetTopRepo() throws CTSBusinessException {
    when(gitClient.getAllRepo(any(), any(),any())).thenReturn(getRepoData());
    RepositoriesInfo repositoriesInfo = gitService.getTopRepo(1);
    Assert.assertEquals(1, repositoriesInfo.getRepositoriesInfoList().size());
    Assert.assertEquals("https://github.com/jverkoey/ObjQREncoder", repositoriesInfo.getRepositoriesInfoList().get(0).getHtml_url());
    Assert.assertEquals("Objective-C QR Encoder", repositoriesInfo.getRepositoriesInfoList().get(0).getDescription());
    Assert.assertEquals("Objective-C", repositoriesInfo.getRepositoriesInfoList().get(0).getLanguage());
    Assert.assertEquals("ObjQREncoder", repositoriesInfo.getRepositoriesInfoList().get(0).getName());
  }

  @Test(expected = Exception.class)
  public void shouldAbleToHandleExceptionInGetRepo() throws CTSBusinessException {
    when(gitClient.getAllRepo(any(), any(),any())).thenThrow(Exception.class);
    RepositoriesInfo repositoriesInfo = gitService.getTopRepo(1);
  }

  @Test(expected = CTSBusinessException.class)
  public void shouldAbleToNullResponse() throws CTSBusinessException {
    when(gitClient.getAllRepo(any(), any(),any())).thenReturn(null);
    RepositoriesInfo repositoriesInfo = gitService.getTopRepo(1);
  }


  @Test
  public void shouldAbleToNullResponseWithErrorCodeandMessage() throws CTSBusinessException {
    when(gitClient.getAllRepo(any(), any(),any())).thenReturn(null);
    try {
      RepositoriesInfo repositoriesInfo = gitService.getTopRepo(1);
      fail();
    } catch (CTSBusinessException exp) {
      Assert.assertEquals(HttpStatus
              .NOT_FOUND.value(),exp.getErrorCode());
    }
  }

  @Test
  public void shouldAbleTogetUser() throws CTSBusinessException {
    when(gitClient.getAllRepo(any(), any(),any())).thenReturn(getRepoData());
    Users users = gitService.getUserWithZeroFllower(1);
    Assert.assertEquals(1, users.getItems().size());
    Assert.assertEquals("https://github.com/jverkoey/ObjQREncoder",
            users.getItems().get(0).getHtml_url());
    Assert.assertEquals("jverkoey", users.getItems().get(0).getLogin());
    Assert.assertEquals("367872", users.getItems().get(0).getId());
  }

  @Test(expected = Exception.class)
  public void shouldAbleToHandleExceptionInGetUser() throws CTSBusinessException {
    when(gitClient.getAllRepo(any(), any(),any())).thenThrow(Exception.class);
    Users users = gitService.getUserWithZeroFllower(1);
  }

  @Test(expected = CTSBusinessException.class)
  public void shouldAbleToNullResponseInUser() throws CTSBusinessException {
    when(gitClient.getAllRepo(any(), any(),any())).thenReturn(null);
    Users users = gitService.getUserWithZeroFllower(1);
  }


  @Test
  public void shouldAbleToNullResponseWithErrorCodeandMessageInUser() throws CTSBusinessException {
    when(gitClient.getAllRepo(any(), any(),any())).thenReturn(null);
    try {
      Users users = gitService.getUserWithZeroFllower(1);
      fail();
    } catch (CTSBusinessException exp) {
      Assert.assertEquals(HttpStatus
              .NOT_FOUND.value(),exp.getErrorCode());
    }
  }


  private String getRepoData() {
    return "{\n" +
            "  \"total_count\": 33101073,\n" +
            "  \"incomplete_results\": true,\n" +
            "  \"items\": [\n" +
            "    {\n" +
            "      \"id\": 367872,\n" +
            "      \"node_id\": \"MDEwOlJlcG9zaXRvcnkzNjc4NzI=\",\n" +
            "      \"name\": \"ObjQREncoder\",\n" +
            "      \"full_name\": \"jverkoey/ObjQREncoder\",\n" +
            "      \"private\": false,\n" +
            "      \"owner\": {\n" +
            "        \"login\": \"jverkoey\",\n" +
            "        \"id\": 45670,\n" +
            "        \"node_id\": \"MDQ6VXNlcjQ1Njcw\",\n" +
            "        \"avatar_url\": \"https://avatars0.githubusercontent.com/u/45670?v=4\",\n" +
            "        \"gravatar_id\": \"\",\n" +
            "        \"url\": \"https://api.github.com/users/jverkoey\",\n" +
            "        \"html_url\": \"https://github.com/jverkoey\",\n" +
            "        \"followers_url\": \"https://api.github.com/users/jverkoey/followers\",\n" +
            "        \"following_url\": \"https://api.github.com/users/jverkoey/following{/other_user}\",\n" +
            "        \"gists_url\": \"https://api.github.com/users/jverkoey/gists{/gist_id}\",\n" +
            "        \"starred_url\": \"https://api.github.com/users/jverkoey/starred{/owner}{/repo}\",\n" +
            "        \"subscriptions_url\": \"https://api.github.com/users/jverkoey/subscriptions\",\n" +
            "        \"organizations_url\": \"https://api.github.com/users/jverkoey/orgs\",\n" +
            "        \"repos_url\": \"https://api.github.com/users/jverkoey/repos\",\n" +
            "        \"events_url\": \"https://api.github.com/users/jverkoey/events{/privacy}\",\n" +
            "        \"received_events_url\": \"https://api.github.com/users/jverkoey/received_events\",\n" +
            "        \"type\": \"User\",\n" +
            "        \"site_admin\": false\n" +
            "      },\n" +
            "      \"html_url\": \"https://github.com/jverkoey/ObjQREncoder\",\n" +
            "      \"description\": \"Objective-C QR Encoder\",\n" +
            "      \"fork\": false,\n" +
            "      \"url\": \"https://api.github.com/repos/jverkoey/ObjQREncoder\",\n" +
            "      \"forks_url\": \"https://api.github.com/repos/jverkoey/ObjQREncoder/forks\",\n" +
            "      \"keys_url\": \"https://api.github.com/repos/jverkoey/ObjQREncoder/keys{/key_id}\",\n" +
            "      \"collaborators_url\": \"https://api.github" +
            ".com/repos/jverkoey/ObjQREncoder/collaborators{/collaborator}\",\n" +
            "      \"teams_url\": \"https://api.github.com/repos/jverkoey/ObjQREncoder/teams\",\n" +
            "      \"hooks_url\": \"https://api.github.com/repos/jverkoey/ObjQREncoder/hooks\",\n" +
            "      \"issue_events_url\": \"https://api.github" +
            ".com/repos/jverkoey/ObjQREncoder/issues/events{/number}\",\n" +
            "      \"events_url\": \"https://api.github.com/repos/jverkoey/ObjQREncoder/events\",\n" +
            "      \"assignees_url\": \"https://api.github.com/repos/jverkoey/ObjQREncoder/assignees{/user}\",\n" +
            "      \"branches_url\": \"https://api.github.com/repos/jverkoey/ObjQREncoder/branches{/branch}\",\n" +
            "      \"tags_url\": \"https://api.github.com/repos/jverkoey/ObjQREncoder/tags\",\n" +
            "      \"blobs_url\": \"https://api.github.com/repos/jverkoey/ObjQREncoder/git/blobs{/sha}\",\n" +
            "      \"git_tags_url\": \"https://api.github.com/repos/jverkoey/ObjQREncoder/git/tags{/sha}\",\n" +
            "      \"git_refs_url\": \"https://api.github.com/repos/jverkoey/ObjQREncoder/git/refs{/sha}\",\n" +
            "      \"trees_url\": \"https://api.github.com/repos/jverkoey/ObjQREncoder/git/trees{/sha}\",\n" +
            "      \"statuses_url\": \"https://api.github.com/repos/jverkoey/ObjQREncoder/statuses/{sha}\",\n" +
            "      \"languages_url\": \"https://api.github.com/repos/jverkoey/ObjQREncoder/languages\",\n" +
            "      \"stargazers_url\": \"https://api.github.com/repos/jverkoey/ObjQREncoder/stargazers\",\n" +
            "      \"contributors_url\": \"https://api.github.com/repos/jverkoey/ObjQREncoder/contributors\",\n" +
            "      \"subscribers_url\": \"https://api.github.com/repos/jverkoey/ObjQREncoder/subscribers\",\n" +
            "      \"subscription_url\": \"https://api.github.com/repos/jverkoey/ObjQREncoder/subscription\",\n" +
            "      \"commits_url\": \"https://api.github.com/repos/jverkoey/ObjQREncoder/commits{/sha}\",\n" +
            "      \"git_commits_url\": \"https://api.github.com/repos/jverkoey/ObjQREncoder/git/commits{/sha}\",\n" +
            "      \"comments_url\": \"https://api.github.com/repos/jverkoey/ObjQREncoder/comments{/number}\",\n" +
            "      \"issue_comment_url\": \"https://api.github" +
            ".com/repos/jverkoey/ObjQREncoder/issues/comments{/number}\",\n" +
            "      \"contents_url\": \"https://api.github.com/repos/jverkoey/ObjQREncoder/contents/{+path}\",\n" +
            "      \"compare_url\": \"https://api.github.com/repos/jverkoey/ObjQREncoder/compare/{base}...{head}\",\n" +
            "      \"merges_url\": \"https://api.github.com/repos/jverkoey/ObjQREncoder/merges\",\n" +
            "      \"archive_url\": \"https://api.github.com/repos/jverkoey/ObjQREncoder/{archive_format}{/ref}\",\n" +
            "      \"downloads_url\": \"https://api.github.com/repos/jverkoey/ObjQREncoder/downloads\",\n" +
            "      \"issues_url\": \"https://api.github.com/repos/jverkoey/ObjQREncoder/issues{/number}\",\n" +
            "      \"pulls_url\": \"https://api.github.com/repos/jverkoey/ObjQREncoder/pulls{/number}\",\n" +
            "      \"milestones_url\": \"https://api.github.com/repos/jverkoey/ObjQREncoder/milestones{/number}\",\n" +
            "      \"notifications_url\": \"https://api.github.com/repos/jverkoey/ObjQREncoder/notifications{?since," +
            "all,participating}\",\n" +
            "      \"labels_url\": \"https://api.github.com/repos/jverkoey/ObjQREncoder/labels{/name}\",\n" +
            "      \"releases_url\": \"https://api.github.com/repos/jverkoey/ObjQREncoder/releases{/id}\",\n" +
            "      \"deployments_url\": \"https://api.github.com/repos/jverkoey/ObjQREncoder/deployments\",\n" +
            "      \"created_at\": \"2009-11-10T18:42:57Z\",\n" +
            "      \"updated_at\": \"2019-08-13T14:29:10Z\",\n" +
            "      \"pushed_at\": \"2011-09-23T07:45:34Z\",\n" +
            "      \"git_url\": \"git://github.com/jverkoey/ObjQREncoder.git\",\n" +
            "      \"ssh_url\": \"git@github.com:jverkoey/ObjQREncoder.git\",\n" +
            "      \"clone_url\": \"https://github.com/jverkoey/ObjQREncoder.git\",\n" +
            "      \"svn_url\": \"https://github.com/jverkoey/ObjQREncoder\",\n" +
            "      \"homepage\": \"\",\n" +
            "      \"size\": 150,\n" +
            "      \"stargazers_count\": 396,\n" +
            "      \"watchers_count\": 396,\n" +
            "      \"language\": \"Objective-C\",\n" +
            "      \"has_issues\": true,\n" +
            "      \"has_projects\": true,\n" +
            "      \"has_downloads\": true,\n" +
            "      \"has_wiki\": true,\n" +
            "      \"has_pages\": false,\n" +
            "      \"forks_count\": 108,\n" +
            "      \"mirror_url\": null,\n" +
            "      \"archived\": false,\n" +
            "      \"disabled\": false,\n" +
            "      \"open_issues_count\": 9,\n" +
            "      \"license\": null,\n" +
            "      \"forks\": 108,\n" +
            "      \"open_issues\": 9,\n" +
            "      \"watchers\": 396,\n" +
            "      \"default_branch\": \"master\",\n" +
            "      \"score\": 1\n" +
            "    },\n" +
            "    {\n" +
            "      \"id\": 3436550,\n" +
            "      \"node_id\": \"MDEwOlJlcG9zaXRvcnkzNDM2NTUw\",\n" +
            "      \"name\": \"Flickable.js\",\n" +
            "      \"full_name\": \"tomlongo/Flickable.js\",\n" +
            "      \"private\": false,\n" +
            "      \"owner\": {\n" +
            "        \"login\": \"tomlongo\",\n" +
            "        \"id\": 1435382,\n" +
            "        \"node_id\": \"MDQ6VXNlcjE0MzUzODI=\",\n" +
            "        \"avatar_url\": \"https://avatars0.githubusercontent.com/u/1435382?v=4\",\n" +
            "        \"gravatar_id\": \"\",\n" +
            "        \"url\": \"https://api.github.com/users/tomlongo\",\n" +
            "        \"html_url\": \"https://github.com/tomlongo\",\n" +
            "        \"followers_url\": \"https://api.github.com/users/tomlongo/followers\",\n" +
            "        \"following_url\": \"https://api.github.com/users/tomlongo/following{/other_user}\",\n" +
            "        \"gists_url\": \"https://api.github.com/users/tomlongo/gists{/gist_id}\",\n" +
            "        \"starred_url\": \"https://api.github.com/users/tomlongo/starred{/owner}{/repo}\",\n" +
            "        \"subscriptions_url\": \"https://api.github.com/users/tomlongo/subscriptions\",\n" +
            "        \"organizations_url\": \"https://api.github.com/users/tomlongo/orgs\",\n" +
            "        \"repos_url\": \"https://api.github.com/users/tomlongo/repos\",\n" +
            "        \"events_url\": \"https://api.github.com/users/tomlongo/events{/privacy}\",\n" +
            "        \"received_events_url\": \"https://api.github.com/users/tomlongo/received_events\",\n" +
            "        \"type\": \"User\",\n" +
            "        \"site_admin\": false\n" +
            "      },\n" +
            "      \"html_url\": \"https://github.com/tomlongo/Flickable.js\",\n" +
            "      \"description\": \"Flickable: A Zepto Plugin to Enable Touch Gestures on Any HTML Element\",\n" +
            "      \"fork\": false,\n" +
            "      \"url\": \"https://api.github.com/repos/tomlongo/Flickable.js\",\n" +
            "      \"forks_url\": \"https://api.github.com/repos/tomlongo/Flickable.js/forks\",\n" +
            "      \"keys_url\": \"https://api.github.com/repos/tomlongo/Flickable.js/keys{/key_id}\",\n" +
            "      \"collaborators_url\": \"https://api.github.com/repos/tomlongo/Flickable" +
            ".js/collaborators{/collaborator}\",\n" +
            "      \"teams_url\": \"https://api.github.com/repos/tomlongo/Flickable.js/teams\",\n" +
            "      \"hooks_url\": \"https://api.github.com/repos/tomlongo/Flickable.js/hooks\",\n" +
            "      \"issue_events_url\": \"https://api.github.com/repos/tomlongo/Flickable" +
            ".js/issues/events{/number}\",\n" +
            "      \"events_url\": \"https://api.github.com/repos/tomlongo/Flickable.js/events\",\n" +
            "      \"assignees_url\": \"https://api.github.com/repos/tomlongo/Flickable.js/assignees{/user}\",\n" +
            "      \"branches_url\": \"https://api.github.com/repos/tomlongo/Flickable.js/branches{/branch}\",\n" +
            "      \"tags_url\": \"https://api.github.com/repos/tomlongo/Flickable.js/tags\",\n" +
            "      \"blobs_url\": \"https://api.github.com/repos/tomlongo/Flickable.js/git/blobs{/sha}\",\n" +
            "      \"git_tags_url\": \"https://api.github.com/repos/tomlongo/Flickable.js/git/tags{/sha}\",\n" +
            "      \"git_refs_url\": \"https://api.github.com/repos/tomlongo/Flickable.js/git/refs{/sha}\",\n" +
            "      \"trees_url\": \"https://api.github.com/repos/tomlongo/Flickable.js/git/trees{/sha}\",\n" +
            "      \"statuses_url\": \"https://api.github.com/repos/tomlongo/Flickable.js/statuses/{sha}\",\n" +
            "      \"languages_url\": \"https://api.github.com/repos/tomlongo/Flickable.js/languages\",\n" +
            "      \"stargazers_url\": \"https://api.github.com/repos/tomlongo/Flickable.js/stargazers\",\n" +
            "      \"contributors_url\": \"https://api.github.com/repos/tomlongo/Flickable.js/contributors\",\n" +
            "      \"subscribers_url\": \"https://api.github.com/repos/tomlongo/Flickable.js/subscribers\",\n" +
            "      \"subscription_url\": \"https://api.github.com/repos/tomlongo/Flickable.js/subscription\",\n" +
            "      \"commits_url\": \"https://api.github.com/repos/tomlongo/Flickable.js/commits{/sha}\",\n" +
            "      \"git_commits_url\": \"https://api.github.com/repos/tomlongo/Flickable.js/git/commits{/sha}\",\n" +
            "      \"comments_url\": \"https://api.github.com/repos/tomlongo/Flickable.js/comments{/number}\",\n" +
            "      \"issue_comment_url\": \"https://api.github.com/repos/tomlongo/Flickable" +
            ".js/issues/comments{/number}\",\n" +
            "      \"contents_url\": \"https://api.github.com/repos/tomlongo/Flickable.js/contents/{+path}\",\n" +
            "      \"compare_url\": \"https://api.github.com/repos/tomlongo/Flickable.js/compare/{base}...{head}\",\n" +
            "      \"merges_url\": \"https://api.github.com/repos/tomlongo/Flickable.js/merges\",\n" +
            "      \"archive_url\": \"https://api.github.com/repos/tomlongo/Flickable.js/{archive_format}{/ref}\",\n" +
            "      \"downloads_url\": \"https://api.github.com/repos/tomlongo/Flickable.js/downloads\",\n" +
            "      \"issues_url\": \"https://api.github.com/repos/tomlongo/Flickable.js/issues{/number}\",\n" +
            "      \"pulls_url\": \"https://api.github.com/repos/tomlongo/Flickable.js/pulls{/number}\",\n" +
            "      \"milestones_url\": \"https://api.github.com/repos/tomlongo/Flickable.js/milestones{/number}\",\n" +
            "      \"notifications_url\": \"https://api.github.com/repos/tomlongo/Flickable.js/notifications{?since," +
            "all,participating}\",\n" +
            "      \"labels_url\": \"https://api.github.com/repos/tomlongo/Flickable.js/labels{/name}\",\n" +
            "      \"releases_url\": \"https://api.github.com/repos/tomlongo/Flickable.js/releases{/id}\",\n" +
            "      \"deployments_url\": \"https://api.github.com/repos/tomlongo/Flickable.js/deployments\",\n" +
            "      \"created_at\": \"2012-02-14T02:59:58Z\",\n" +
            "      \"updated_at\": \"2019-10-13T21:17:28Z\",\n" +
            "      \"pushed_at\": \"2013-04-03T05:18:15Z\",\n" +
            "      \"git_url\": \"git://github.com/tomlongo/Flickable.js.git\",\n" +
            "      \"ssh_url\": \"git@github.com:tomlongo/Flickable.js.git\",\n" +
            "      \"clone_url\": \"https://github.com/tomlongo/Flickable.js.git\",\n" +
            "      \"svn_url\": \"https://github.com/tomlongo/Flickable.js\",\n" +
            "      \"homepage\": null,\n" +
            "      \"size\": 492,\n" +
            "      \"stargazers_count\": 368,\n" +
            "      \"watchers_count\": 368,\n" +
            "      \"language\": \"JavaScript\",\n" +
            "      \"has_issues\": true,\n" +
            "      \"has_projects\": true,\n" +
            "      \"has_downloads\": true,\n" +
            "      \"has_wiki\": true,\n" +
            "      \"has_pages\": false,\n" +
            "      \"forks_count\": 43,\n" +
            "      \"mirror_url\": null,\n" +
            "      \"archived\": false,\n" +
            "      \"disabled\": false,\n" +
            "      \"open_issues_count\": 9,\n" +
            "      \"license\": null,\n" +
            "      \"forks\": 43,\n" +
            "      \"open_issues\": 9,\n" +
            "      \"watchers\": 368,\n" +
            "      \"default_branch\": \"master\",\n" +
            "      \"score\": 1\n" +
            "    },\n" +
            "    {\n" +
            "      \"id\": 4163899,\n" +
            "      \"node_id\": \"MDEwOlJlcG9zaXRvcnk0MTYzODk5\",\n" +
            "      \"name\": \"pathod\",\n" +
            "      \"full_name\": \"mitmproxy/pathod\",\n" +
            "      \"private\": false,\n" +
            "      \"owner\": {\n" +
            "        \"login\": \"mitmproxy\",\n" +
            "        \"id\": 4652787,\n" +
            "        \"node_id\": \"MDEyOk9yZ2FuaXphdGlvbjQ2NTI3ODc=\",\n" +
            "        \"avatar_url\": \"https://avatars3.githubusercontent.com/u/4652787?v=4\",\n" +
            "        \"gravatar_id\": \"\",\n" +
            "        \"url\": \"https://api.github.com/users/mitmproxy\",\n" +
            "        \"html_url\": \"https://github.com/mitmproxy\",\n" +
            "        \"followers_url\": \"https://api.github.com/users/mitmproxy/followers\",\n" +
            "        \"following_url\": \"https://api.github.com/users/mitmproxy/following{/other_user}\",\n" +
            "        \"gists_url\": \"https://api.github.com/users/mitmproxy/gists{/gist_id}\",\n" +
            "        \"starred_url\": \"https://api.github.com/users/mitmproxy/starred{/owner}{/repo}\",\n" +
            "        \"subscriptions_url\": \"https://api.github.com/users/mitmproxy/subscriptions\",\n" +
            "        \"organizations_url\": \"https://api.github.com/users/mitmproxy/orgs\",\n" +
            "        \"repos_url\": \"https://api.github.com/users/mitmproxy/repos\",\n" +
            "        \"events_url\": \"https://api.github.com/users/mitmproxy/events{/privacy}\",\n" +
            "        \"received_events_url\": \"https://api.github.com/users/mitmproxy/received_events\",\n" +
            "        \"type\": \"Organization\",\n" +
            "        \"site_admin\": false\n" +
            "      },\n" +
            "      \"html_url\": \"https://github.com/mitmproxy/pathod\",\n" +
            "      \"description\": \"\uD83D\uDDC3️ This repository has been integrated into mitmproxy/mitmproxy.\"," +
            "\n" +
            "      \"fork\": false,\n" +
            "      \"url\": \"https://api.github.com/repos/mitmproxy/pathod\",\n" +
            "      \"forks_url\": \"https://api.github.com/repos/mitmproxy/pathod/forks\",\n" +
            "      \"keys_url\": \"https://api.github.com/repos/mitmproxy/pathod/keys{/key_id}\",\n" +
            "      \"collaborators_url\": \"https://api.github" +
            ".com/repos/mitmproxy/pathod/collaborators{/collaborator}\",\n" +
            "      \"teams_url\": \"https://api.github.com/repos/mitmproxy/pathod/teams\",\n" +
            "      \"hooks_url\": \"https://api.github.com/repos/mitmproxy/pathod/hooks\",\n" +
            "      \"issue_events_url\": \"https://api.github.com/repos/mitmproxy/pathod/issues/events{/number}\",\n" +
            "      \"events_url\": \"https://api.github.com/repos/mitmproxy/pathod/events\",\n" +
            "      \"assignees_url\": \"https://api.github.com/repos/mitmproxy/pathod/assignees{/user}\",\n" +
            "      \"branches_url\": \"https://api.github.com/repos/mitmproxy/pathod/branches{/branch}\",\n" +
            "      \"tags_url\": \"https://api.github.com/repos/mitmproxy/pathod/tags\",\n" +
            "      \"blobs_url\": \"https://api.github.com/repos/mitmproxy/pathod/git/blobs{/sha}\",\n" +
            "      \"git_tags_url\": \"https://api.github.com/repos/mitmproxy/pathod/git/tags{/sha}\",\n" +
            "      \"git_refs_url\": \"https://api.github.com/repos/mitmproxy/pathod/git/refs{/sha}\",\n" +
            "      \"trees_url\": \"https://api.github.com/repos/mitmproxy/pathod/git/trees{/sha}\",\n" +
            "      \"statuses_url\": \"https://api.github.com/repos/mitmproxy/pathod/statuses/{sha}\",\n" +
            "      \"languages_url\": \"https://api.github.com/repos/mitmproxy/pathod/languages\",\n" +
            "      \"stargazers_url\": \"https://api.github.com/repos/mitmproxy/pathod/stargazers\",\n" +
            "      \"contributors_url\": \"https://api.github.com/repos/mitmproxy/pathod/contributors\",\n" +
            "      \"subscribers_url\": \"https://api.github.com/repos/mitmproxy/pathod/subscribers\",\n" +
            "      \"subscription_url\": \"https://api.github.com/repos/mitmproxy/pathod/subscription\",\n" +
            "      \"commits_url\": \"https://api.github.com/repos/mitmproxy/pathod/commits{/sha}\",\n" +
            "      \"git_commits_url\": \"https://api.github.com/repos/mitmproxy/pathod/git/commits{/sha}\",\n" +
            "      \"comments_url\": \"https://api.github.com/repos/mitmproxy/pathod/comments{/number}\",\n" +
            "      \"issue_comment_url\": \"https://api.github.com/repos/mitmproxy/pathod/issues/comments{/number}\"," +
            "\n" +
            "      \"contents_url\": \"https://api.github.com/repos/mitmproxy/pathod/contents/{+path}\",\n" +
            "      \"compare_url\": \"https://api.github.com/repos/mitmproxy/pathod/compare/{base}...{head}\",\n" +
            "      \"merges_url\": \"https://api.github.com/repos/mitmproxy/pathod/merges\",\n" +
            "      \"archive_url\": \"https://api.github.com/repos/mitmproxy/pathod/{archive_format}{/ref}\",\n" +
            "      \"downloads_url\": \"https://api.github.com/repos/mitmproxy/pathod/downloads\",\n" +
            "      \"issues_url\": \"https://api.github.com/repos/mitmproxy/pathod/issues{/number}\",\n" +
            "      \"pulls_url\": \"https://api.github.com/repos/mitmproxy/pathod/pulls{/number}\",\n" +
            "      \"milestones_url\": \"https://api.github.com/repos/mitmproxy/pathod/milestones{/number}\",\n" +
            "      \"notifications_url\": \"https://api.github.com/repos/mitmproxy/pathod/notifications{?since,all," +
            "participating}\",\n" +
            "      \"labels_url\": \"https://api.github.com/repos/mitmproxy/pathod/labels{/name}\",\n" +
            "      \"releases_url\": \"https://api.github.com/repos/mitmproxy/pathod/releases{/id}\",\n" +
            "      \"deployments_url\": \"https://api.github.com/repos/mitmproxy/pathod/deployments\",\n" +
            "      \"created_at\": \"2012-04-28T00:42:40Z\",\n" +
            "      \"updated_at\": \"2019-08-15T21:53:00Z\",\n" +
            "      \"pushed_at\": \"2016-02-18T23:01:58Z\",\n" +
            "      \"git_url\": \"git://github.com/mitmproxy/pathod.git\",\n" +
            "      \"ssh_url\": \"git@github.com:mitmproxy/pathod.git\",\n" +
            "      \"clone_url\": \"https://github.com/mitmproxy/pathod.git\",\n" +
            "      \"svn_url\": \"https://github.com/mitmproxy/pathod\",\n" +
            "      \"homepage\": \"\",\n" +
            "      \"size\": 1171,\n" +
            "      \"stargazers_count\": 356,\n" +
            "      \"watchers_count\": 356,\n" +
            "      \"language\": \"Python\",\n" +
            "      \"has_issues\": false,\n" +
            "      \"has_projects\": true,\n" +
            "      \"has_downloads\": true,\n" +
            "      \"has_wiki\": false,\n" +
            "      \"has_pages\": false,\n" +
            "      \"forks_count\": 29,\n" +
            "      \"mirror_url\": null,\n" +
            "      \"archived\": true,\n" +
            "      \"disabled\": false,\n" +
            "      \"open_issues_count\": 0,\n" +
            "      \"license\": {\n" +
            "        \"key\": \"mit\",\n" +
            "        \"name\": \"MIT License\",\n" +
            "        \"spdx_id\": \"MIT\",\n" +
            "        \"url\": \"https://api.github.com/licenses/mit\",\n" +
            "        \"node_id\": \"MDc6TGljZW5zZTEz\"\n" +
            "      },\n" +
            "      \"forks\": 29,\n" +
            "      \"open_issues\": 0,\n" +
            "      \"watchers\": 356,\n" +
            "      \"default_branch\": \"master\",\n" +
            "      \"score\": 1\n" +
            "    }\n" +
            "  ]\n" +
            "}";
  }

}
