# REST endpoints

| Endpoint | Operation Type | Purpose |
| --- | --- | --- |
| /tools/  | GET | Returns all available tools from the application
| /tools/{id}  | GET | Returns a course with the supplied tool ID
| /tools/availability/{status}  | GET | Returns the list of tools with the supplied tool availability name
| /tools/  | POST | Create a new tool
| /tools/{id}  | PUT | Update the tool for the supplied tool ID
| /tools/{id}  | DELETE | Deletes a tool with the supplied tool ID
| /tools/  | DELETE | Deletes all tools from the application
