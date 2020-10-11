# Contributing
Please follow the following rules in all your interactions with the project.

## Pull Request Process
- Ensure any build dependencies when doing a build.
- Branching: for any feature/bug-fix create a branch from master:
```sh
git checkout master
git pull
git checkout -b your-branch-name master
```

branch name must be in this format: `{client|server}-{feature-name}`. (write client prefix if you are modifying client directory and so on)

- check tests before open pull request:
```sh
./gradlew test
```

- Open a PR. PR title must be in following format: `[client|server]: your pr title`

- Make sure your PR passed all the tests

- Done! Cheers :beers:
