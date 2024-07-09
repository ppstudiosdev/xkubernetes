package de.pixelplayer_studios.xkubernetes;

import io.fabric8.kubernetes.api.model.*;
import io.fabric8.kubernetes.api.model.apps.Deployment;
import io.fabric8.kubernetes.client.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.stream.Collectors;

@SuppressWarnings({"deprecation", "DataFlowIssue", "unused"})
public class xKubernetes extends JavaPlugin {

    private KubernetesClient client; // Kubernetes client for interacting with the API

    @Override
    public void onEnable() {
        // Configure the Kubernetes client on plugin enable
        client = new DefaultKubernetesClient();

        // Register the command handler for the /kubernetes command
        getCommand("kubernetes").setExecutor(this);
    }

    @Override
    public void onDisable() {
        // Clean up and close the Kubernetes client on plugin disable
        client.close();
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, Command command, @NotNull String label, String[] args) {
        if (!command.getName().equalsIgnoreCase("kubernetes")) {
            return false;
        }

        if (args.length == 0) {
            sender.sendMessage("Usage: /kubernetes <info|services|deployments|pods>");
            return true;
        }

        // Process sub-commands for /kubernetes
        switch (args[0].toLowerCase()) {
            case "info":
                return handleKubernetesInfo(sender);
            case "services":
                return handleKubernetesServices(sender);
            case "deployments":
                return handleKubernetesDeployments(sender);
            case "pods":
                return handleKubernetesPods(sender);
            default:
                sender.sendMessage("Unknown sub-command. Usage: /kubernetes <info|services|deployments|pods>");
                return true;
        }
    }

    /**
     * Handles the "info" sub-command for Kubernetes cluster information.
     *
     * @param sender The command sender
     * @return true if the command was processed successfully
     */
    private boolean handleKubernetesInfo(CommandSender sender) {
        String clusterName = client.getNamespace();
        String serverVersion = client.getVersion().getGitVersion();

        sender.sendMessage("Kubernetes Cluster Info:");
        sender.sendMessage("Cluster Name: " + clusterName);
        sender.sendMessage("Server Version: " + serverVersion);

        return true;
    }

    /**
     * Handles the "services" sub-command for listing Kubernetes services.
     *
     * @param sender The command sender
     * @return true if the command was processed successfully
     */
    private boolean handleKubernetesServices(CommandSender sender) {
        List<Service> services = client.services().inAnyNamespace().list().getItems();
        List<String> serviceNames = services.stream()
                .map(service -> service.getMetadata().getName())
                .collect(Collectors.toList());

        sender.sendMessage("Kubernetes Services:");
        sender.sendMessage(String.join(", ", serviceNames));

        return true;
    }

    /**
     * Handles the "deployments" sub-command for listing Kubernetes deployments.
     *
     * @param sender The command sender
     * @return true if the command was processed successfully
     */
    private boolean handleKubernetesDeployments(CommandSender sender) {
        List<Deployment> deployments = client.apps().deployments().inAnyNamespace().list().getItems();
        List<String> deploymentNames = deployments.stream()
                .map(deployment -> deployment.getMetadata().getName())
                .collect(Collectors.toList());

        sender.sendMessage("Kubernetes Deployments:");
        sender.sendMessage(String.join(", ", deploymentNames));

        return true;
    }

    /**
     * Handles the "pods" sub-command for listing Kubernetes pods.
     *
     * @param sender The command sender
     * @return true if the command was processed successfully
     */
    private boolean handleKubernetesPods(CommandSender sender) {
        List<Pod> pods = client.pods().inAnyNamespace().list().getItems();
        List<String> podNames = pods.stream()
                .map(pod -> pod.getMetadata().getName())
                .collect(Collectors.toList());

        sender.sendMessage("Kubernetes Pods:");
        sender.sendMessage(String.join(", ", podNames));

        return true;
    }
}
