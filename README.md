Nexus-rundeck-plugin
========
Nexus RunDeck plugin
https://github.com/moifort/nexus-rundeck-plugin
This is a Nexus plugin that provides an Option provider for RunDeck.

# Required
* Sanotype Nexus: v2.11.1-01 (Plugin has only tested with this version)
* Rundeck: v2.4.0-1
* Nexus-rundeck-plugin: https://github.com/moifort/nexus-rundeck-plugin/releases/download/2.11.1-01/rundeck-nexus-plugin-2.11.1-01-bundle.zip

# How to
On the Nexus application, in my case I use a docker "sonatype/nexus" image, more information here: https://registry.hub.docker.com/u/sonatype/nexus/.

1.  Put the folder rundeck-nexus-plugin-2.11.1-01 (Download from https://github.com/moifort/nexus-rundeck-plugin/releases/download/2.11.1-01/rundeck-nexus-plugin-2.11.1-01-bundle.zip) in the folder plugin-repository/ of your Nexus app (or directly /sonatype-work/plugin-repository/ in my case). Your folder must be like (in my case): /sonatype-work/plugin-repository/rundeck-nexus-plugin-2.11.1-01/
2. Restart your Nexus app
3 - Login to your Nexus app and go the menu "Administrator/Plugin Console" like bellow:
Images intégrées 2
4 - Normally you will have the rundeck plugin with the status: "activated", if it doesn't work it's status: "Broken" (it's not good)
5 - Now, test if you receive a JSON file enter the URL like below:
http://mynexus/service/local/rundeck/options/version?r=snapshots&g=com.compagny&a=myproject
Nexus App hostname: mynexus
Repository: snapshots
GroupId: com.compagny
Project: myproject

If you already deploy some snapshot version of your project on your Nexus app you will have a JSON like below:
[{"name":"1.0.3-SNAPSHOT (2015-01-23T15:45:41)","value":"1.0.3-SNAPSHOT"},{"name":"1.0.2-SNAPSHOT (2015-01-23T15:29:17)","value":"1.0.2-SNAPSHOT"},{"name":"1.0.1-SNAPSHOT (2015-01-21T11:15:12)","value":"1.0.1-SNAPSHOT"},{"name":"1.0-SNAPSHOT (2015-01-14T17:18:39)","value":"1.0-SNAPSHOT"}]
6 - OK, everything work, now we go to configure the Rundeck job.

On my Rundeck v2.4.0-1 app (same I use a docker):
1 - Edit your job 
2 - In the "Options" part click on "Add an option" like below:
Images intégrées 4


3 - Edit your option like below (Change in red): In the remote URL you put your URL we test before
Images intégrées 5


4 - Save your job and know you have this, you can select your version you want to deploy:
Images intégrées 6



Usage
The plugin provides the following new HTTP resources :
- http://NEXUS_HOST/service/local/rundeck/options/version : return a json array with the version of the matching artifacts.
  Parameters (all optional) :
  - r : repository ID to search in (null for searching in all indexed repositories)
  - g : groupId of the artifacts to match
  - a : artifactId of the artifacts to match
  - p : packaging of the artifacts to match ('jar', 'war', etc)
  - c : classifier of the artifacts to match ('sources', 'javadoc', etc)
  - l : limit - max number of results to return
  - includeLatest : if "true", will include the special "LATEST" version before all versions
  - includeRelease : if "true", will include the special "RELEASE" version before all versions
  - optional : if "true", will include an empty option before all versions
- http://NEXUS_HOST/service/local/rundeck/options/artifactId : return a json array with the artifactId of the matching artifacts.
  Parameters (all optional) :
  - r : repository ID to search in (null for searching in all indexed repositories)
  - g : groupId of the artifacts to match
  - v : version of the artifacts to match
  - p : packaging of the artifacts to match ('jar', 'war', etc)
  - c : classifier of the artifacts to match ('sources', 'javadoc', etc)
  - optional : if "true", will include an empty option before all artifacts
Note that if you want to retrieve the artifact from your RunDeck script, you should use Nexus REST API : https://docs.sonatype.com/display/SPRTNXOSS/Nexus+FAQ#NexusFAQ-Q.HowcanIretrieveasnapshotifIdon%27tknowtheexactfilename%3F 

Change Log
- Version 2.11.1-01 : Compatibility for Nexus 2.11 
- Version 2.7.2 : with a dependency on Nexus 2.7.2 (instead of 2.8.0), reverted changes to JSON response for version option provider
- Version 1.2.2.2 : re-release 1.2, but with a dependence on Nexus 1.9.2.2 (instead of Nexus 1.9)
- Version 1.2 : the option provider for version now includes the date of the version (release)
- Version 1.1 : add option provider for artifactId
- Version 1.0 : option provider for version. compatible with Nexus 1.9 and RunDeck 1.1

Prequisites
- Apache Maven 3.0.5
- JDK 7
- Sonatype Nexus 2.11.1-01

How to build
- run "mvn package"
- use the "bundle" file in target/nexus-rundeck-plugin-2.11.1-01-bundle.zip

FAQ
- If you get an error while installing the plugin, check this how-to : http://kb.dtosolutions.com/wiki/Nexus-rundeck-plugin_options_service

Resources
- Nexus : http://nexus.sonatype.org
- RunDeck : http://www.rundeck.org
- Maven : http://maven.apache.org/
- JDK 7 : http://www.oracle.com/technetwork/java/javase/overview/index.html 
- RunDeck "Option Model Provider" : http://rundeck.org/docs/RunDeck-Guide.html#option-model-provider
- RunDeck mailing-list (for questions/feedback/etc) : http://groups.google.com/group/rundeck-discuss

LICENSE : The Apache Software License, Version 2.0
See the LICENSE file, or http://www.apache.org/licenses/LICENSE-2.0
