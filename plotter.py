import pandas as pd
import matplotlib.pyplot as plt

df = pd.read_csv('run-results.csv')
# pivot the data for easier plotting
pivot_df = df.pivot(index='ArraySize', columns='AlgorithmName', values='Runtime')

print(pivot_df)

# plot the data
plt.figure("Comparison", figsize=(12, 6))
for algorithm in pivot_df.columns:
    plt.plot(pivot_df.index, pivot_df[algorithm], marker='o', label=algorithm)

plt.xscale('log') # better because x is growing exponentially
plt.xlabel('Array Size')
plt.ylabel('Runtime (in milliseconds)')
plt.title('Comparison Between Different Mean-Finding Algorithms')

plt.legend()
plt.grid(True, which='both', linestyle='--', linewidth=0.5)

plt.show()