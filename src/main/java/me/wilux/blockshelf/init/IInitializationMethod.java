package me.wilux.blockshelf.init;

import me.wilux.blockshelf.util.InitializationState;

public interface IInitializationMethod
{
	public void init(boolean forceUpload);
	public void initResourcePack(boolean forceUpload);
	public InitializationState getInitializationState();
}
