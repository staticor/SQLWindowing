package com.sap.hadoop.windowing.functions2;

import static com.sap.hadoop.Utils.sprintf;

import org.apache.hadoop.hive.ql.udf.generic.GenericUDAFEvaluator;
import org.apache.hadoop.hive.serde2.objectinspector.StructObjectInspector;

import com.sap.hadoop.windowing.WindowingException;
import com.sap.hadoop.windowing.query2.definition.QueryDef;
import com.sap.hadoop.windowing.query2.definition.TableFuncDef;
import com.sap.hadoop.windowing.runtime2.Partition;

/**
 * Based on Hive {@link GenericUDAFEvaluator}. Break up the responsibility of the old AsbtractTableFunction class into a Resolver and Evaluator.
 * 
 *
 */
public abstract class TableFunctionEvaluator
{
	protected TableFunctionResolver resolver;
	protected StructObjectInspector OI;
	protected StructObjectInspector mapOI;
	protected TableFuncDef tDef;
	protected QueryDef qDef;
	String partitionClass;
	int partitionMemSize;
	
	public TableFunctionResolver getResolver()
	{
		return resolver;
	}

	public void setResolver(TableFunctionResolver resolver)
	{
		this.resolver = resolver;
	}
	
	public StructObjectInspector getOutputOI()
	{
		return OI;
	}
	
	public void setTableDef(TableFuncDef tDef)
	{
		this.tDef = tDef;
	}

	public void setQueryDef(QueryDef qDef)
	{
		this.qDef = qDef;
	}

	public void setPartitionClass(String partitionClass)
	{
		this.partitionClass = partitionClass;
	}

	public void setPartitionMemSize(int partitionMemSize)
	{
		this.partitionMemSize = partitionMemSize;
	}

	public StructObjectInspector getMapOutputOI()
	{
		return mapOI;
	}
	
	public TableFuncDef getTableDef()
	{
		return tDef;
	}
	
	public QueryDef getQueryDef()
	{
		return qDef;
	}
	
	public abstract void setupOI() throws WindowingException; 
	
	public void setupMapOI() throws WindowingException
	{
		if (!resolver.hasMapPhase())
		{
			return;
		}
		mapOI = OI;
	}
	
	public String getPartitionClass()
	{
		return partitionClass;
	}

	public int getPartitionMemSize()
	{
		return partitionMemSize;
	}
	
	public boolean hasMapPhase()
	{
		return resolver.hasMapPhase();
	}
	
	public abstract Partition execute(Partition iPart) throws WindowingException;

	public Partition mapExecute(Partition iPart) throws WindowingException
	{
		if ( !resolver.hasMapPhase())
		{
			throw new WindowingException(sprintf("Internal Error: mapExecute called on function (%s)that has no Map Phase", tDef.getName()));
		}
		return _mapExecute(iPart);
	}
	
	protected Partition _mapExecute(Partition iPart) throws WindowingException
	{
		return null;
	}
}
