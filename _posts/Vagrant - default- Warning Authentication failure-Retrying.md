I tried to destroy and re-create my box with the same result : it eventually
timedout, but the provisions were not executed.

After searching the internet and a lot of experimentation, I managed to make it
work by commenting out the following line in my Vagrantfile

\# config.ssh.private_key_path = "\~/.ssh/id_rsa"
