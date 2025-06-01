# coding-junior-java-test-
 <h1> To-Do Finder IntelliJ Plugin</h1>
Project Description:
To-Do Finder is a lightweight IntelliJ IDEA plugin designed to help developers easily locate and manage TODO comments across their codebase. This tool enhances developer productivity by scanning project files for TODO tags and presenting them in a structured and user-friendly tool window within the IDE.
</br>
🔧 Key Features:
📂 Automatic Scanning: Scans all project files for TODO comments when the project opens or on-demand.

🔍 Centralized View: Displays all detected TODO items in a dedicated tool window.

🧭 Navigation Support: Click on any TODO item to jump directly to its location in the source code.

⚙️ Customizable: Supports filtering or extending scanning rules to support FIXME, NOTE, or custom tags.

🧠 Efficient Indexing: Uses intelligent scanning to avoid redundant processing and maintains performance.
</br>
🏗️ Technologies Used:
Java

IntelliJ Platform SDK

Gradle

XML (for plugin metadata)

Swing (for UI components in tool window)
</br>
📂 File Overview:
build.gradle – Build script for the plugin.

plugin.xml – Plugin metadata and configuration.

TodoFinderStartupActivity.java – Registers plugin actions on project startup.

TodoScanner.java – Contains logic to scan files for TODO comments.

TodoToolWindow.java – Handles display and layout of the TODO list.

TodoToolWindowFactory.java – Responsible for creating the tool window.

models/TodoItem.java – Data model representing a single TODO comment.
