package net.staro.lego.api;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import net.minecraft.command.CommandSource;
import net.staro.lego.Lego;
import net.staro.lego.api.trait.Completable;
import net.staro.lego.api.trait.HasDescription;
import net.staro.lego.api.trait.IsUsingManagers;
import net.staro.lego.api.trait.Nameable;

/**
 * Represents a client's command which can be accessible via minecraft chat by the user.
 */
public interface Command extends Nameable, HasDescription, IsUsingManagers, Completable {
    /**
     * A method which allows to execute a sequence of actions whenever the command is entered.
     * @param lego is the Lego instance providing access to various components and functionalities
     * @param builder is the mojang brigadier's builder class used to parse and execute the command.
     */
    void build(Lego lego, LiteralArgumentBuilder<CommandSource> builder);

    /**
     * Registers a command with a chosen dispatcher.
     * @param dispatcher is the core command dispatcher, for registering, parsing, and executing commands.
     */
    void register(CommandDispatcher<CommandSource> dispatcher);

}
