YAML

YAML starts with --- and end with ... This indicates the start and end of a YAML document.


#### YAML List
Nearly every YAML file starts with a list. Each item in the list is a list of key/value pairs
---
# A list of tasty fruits
- Apple
- Orange
- Strawberry
- Mango
...

#### Dictionary
A dictionary is represented in a simple `key: value` form (the colon must be followed by a space):
# An employee record
martin:
    name: Martin D'vloper
    job: Developer
    skill: Elite


#### Boolean
We can specify boolean value (true/false) in several forms:

create_key: yes
needs_agent: no
knows_oop: True
likes_emacs: TRUE
uses_cvs: false



Large Values can span multiple lines using | or >. 
include_newlines: |
            exactly as you see
            will appear these three
            lines of poetry

fold_newlines: >
            this is really a
            single line of text
            despite appearances


Let’s combine what we learned so far in an arbitrary YAML example.
---
# An employee record
name: Martin D'vloper
job: Developer
skill: Elite
employed: True
foods:
    - Apple
    - Orange
    - Strawberry
    - Mango
languages:
    perl: Elite
    python: Elite
    pascal: Lame
education: |
    4 GCSEs
    3 A-Levels
    BSc in the Internet of Things



#### Variables
uses “{{ var }}” for variables. If we want to use prevuiusly declared variabel we must place between "{{  --var-- }}"
foo: "{{ variable }}"

- A colon followed by a space (or newline) ": " is an indicator for a mapping. 
- A space followed by the pound sign " #" starts a comment.


https://docs.ansible.com/ansible/latest/reference_appendices/YAMLSyntax.html
