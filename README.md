# xKubernetes Plugin

The **xKubernetes** plugin integrates with Kubernetes to provide management and information retrieval functionalities within a Minecraft Bukkit server environment.

## Features

- Retrieve cluster information such as cluster name and server version.
- List Kubernetes services, deployments, and pods.
- Command-driven interface (/kubernetes) for easy interaction.

## Installation

1. Download the latest plugin JAR from the [releases page](link_to_releases).
2. Place the downloaded JAR file into the `plugins` folder of your Bukkit server.

## Commands

- **/kubernetes info**: Displays Kubernetes cluster information.
- **/kubernetes services**: Lists all Kubernetes services.
- **/kubernetes deployments**: Lists all Kubernetes deployments.
- **/kubernetes pods**: Lists all Kubernetes pods.

## Usage

1. Start your Bukkit server.
2. Use the `/kubernetes` command followed by one of the sub-commands listed above to retrieve Kubernetes information.

## Configuration

No additional configuration is required. The plugin connects to the Kubernetes API using default configurations.

## Dependencies

- [Fabric8 Kubernetes Client](https://github.com/fabric8io/kubernetes-client)

## Contributing

Contributions are welcome! If you find any issues or have suggestions, please create an issue or submit a pull request.

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.
