Intellij Code Completion for all setter/getter methods of local variable object

https://stackoverflow.com/questions/16988504/intellij-code-completion-for-all-setter-getter-methods-of-local-variable-object

There is a plugin that does this: <https://plugins.jetbrains.com/idea/plugin/9360-generateallsetter>

Go to File > Settings > Plugins and search for a plugin GenerateAllSetter (See the link above) and install it

**NOTE**: It does not need the IDE to restart.

Once the plugin is installed, create an instance of your object/dto. Place your cursor on the initialization line of code and IntelliJ IDEA show the yellow suggestions light-bulb. Click the light bulb(or use Alt+ Enter) and suggestions will appear. Here you can choose to generate setter with or without default value. See images.

[![Setter call suggestions](https://i.stack.imgur.com/rP7sv.png)](https://i.stack.imgur.com/rP7sv.png)

Below are images of the resulting code genarated (both with and without defaults)

Setters without default values

[![Setters without default values](https://i.stack.imgur.com/JEE4b.png)](https://i.stack.imgur.com/JEE4b.png)

Setters with deafult values

[![Setters with deafult values](https://i.stack.imgur.com/wUMAV.png)](https://i.stack.imgur.com/wUMAV.png)
