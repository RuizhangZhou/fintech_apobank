# Using Git
## General commands
1. Clone a repository into a newly created directory (on your PC): `~$ git clone <link>`, for example the project "web-application" via `~$ git clone git@git.rwth-aachen.de:swc/teaching/winter-term-20-21/lab-data-analytics/group-team-zelda/web-application.git` (with SSH)
2. Incorporate changes of a project: `~$ git pull`
3. Show changed files: `~$ git status`
4. Upload changes to the project:
    1. Update the index to prepare the content staged for the next commit `~$ git add <filepath/filename>` (or, if all changed files are to be uploaded  `~$ git add .` - better check in advance, if really wanted by running `~$ git status`)
    2. Add commit `~$ git commit -m "<commit message>"` (commit message: description of change, usually infinitive+noun)
    3. Upload changes: `~$ git push` 

## Structure of our project
Our application is initially located in two folders: "Frontend" and "Backend". These will be divided into branches. In each of these branches you should work on a specific feature (one branch should be created for each feature). The branch for a feature should be named `<feature>` to make it consistent (where <feature> should be replaced by the name of the concrete name of the feature).

You can create the corresponding project branch in the shell by running the command `~$ git branch feature` and switch to it by running `~$ git checkout <feature>`. Afterwards, the synchronization with this repository must be configured in the branch itself via `~$ git push --set-upstream origin <feature>`. To delete a branch (Attention!) you have to execute `~$ git push --delete origin <feature>`.
When a feature version is developed so far that no more errors occur in the context of the application, you can switch to the master branch and run `~$ git merge <feature>` to integrate <feature> into the master branch.
